package com.example.SpringSecondAppTest.ingerdient;

import com.example.SpringSecondAppTest.exception.ErrorMessage;
import com.example.SpringSecondAppTest.exception.IngredientAlreadyExistException;
import com.example.SpringSecondAppTest.exception.IngredientNotFoundException;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDtoMapper;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientWithoutIdDto;
import com.example.SpringSecondAppTest.user_ingredient.UserIngredient;
import com.example.SpringSecondAppTest.user_ingredient.UserIngredientRepository;
import com.example.SpringSecondAppTest.user_ingredient_meal.UserIngredientMeal;
import com.example.SpringSecondAppTest.user_ingredient_meal.UserIngredientMealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final UserIngredientMealRepository userIngredientMealRepository;
    private final UserIngredientRepository userIngredientRepository;

    public List<IngredientDto> findAll(List<IngredientCategory> ingredientCategory) {
        return IngredientDtoMapper.mapToIngredientDtos(ingredientRepository.findForCategories(ingredientCategory));
    }

    public IngredientDto findById(Long id) {
        return IngredientDtoMapper.mapToIngredientDto(ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(String.format(ErrorMessage.INGREDIENT_NOT_FOUND, id))));
    }

    @Transactional
    public IngredientDto save(IngredientWithoutIdDto ingredientDto) {
        String newIngredientName = ingredientDto.name();
        findAndReplaceIngredientName(newIngredientName);
        Ingredient ingredient = IngredientDtoMapper.mapToIngredient(ingredientDto);
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return IngredientDtoMapper.mapToIngredientDto(savedIngredient);
    }

    @Transactional
    public IngredientDto update(Long id, IngredientWithoutIdDto ingredientDto) {
        String updatedName = ingredientDto.name();
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(String.format(ErrorMessage.INGREDIENT_NOT_FOUND, id)));
        findAndReplaceIngredientName(updatedName);
        ingredient.setName(updatedName);
        ingredient.setIngredientCategory(ingredientDto.ingredientCategory());
        return IngredientDtoMapper.mapToIngredientDto(ingredientRepository.save(ingredient));
    }

    @Transactional
    public void delete(Long id) {
        ingredientRepository.findById(id).ifPresent(ingredient -> {
            userIngredientMealRepository.findUserIdsForIngredientId(id)
                    .forEach(user -> {
                        UserIngredient newUserIngredient = IngredientDtoMapper.mapToUserIngredient(ingredient);
                        newUserIngredient.setUser(user);
                        List<UserIngredientMeal> userIngredientMeals = userIngredientMealRepository.findByIngredientIdAndUserId(ingredient.getId(), user.getId());
                        userIngredientMeals.forEach(userIngredientMeal -> {
                            userIngredientMeal.setIngredient(null);
                            userIngredientMeal.setUserIngredient(newUserIngredient);
                        });
                        newUserIngredient.setIngredientMeals(userIngredientMeals);
                        userIngredientRepository.save(newUserIngredient);
                    });
            ingredientRepository.deleteById(id);
        });
    }


    private void findAndReplaceIngredientName(String name) {
        if (ingredientRepository.findByName(name).isPresent()) {
            throw new IngredientAlreadyExistException(ErrorMessage.INGREDIENT_ALREADY_EXIST);
        }
        userIngredientRepository.findByName(name).forEach(ingredient -> {
                    String updatedUserName = name + "_user";
                    int iterator = 1;
                    Long userIdContainUpdatedIngredientName = ingredient.getUser().getId();
                    while (userIngredientRepository.findByUserIdAndName(userIdContainUpdatedIngredientName, updatedUserName).isPresent()) {
                        updatedUserName += iterator++;
                    }
                    ingredient.setName(updatedUserName);
                }
        );
    }
}
