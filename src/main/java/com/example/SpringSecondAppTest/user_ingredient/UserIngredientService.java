package com.example.SpringSecondAppTest.user_ingredient;

import com.example.SpringSecondAppTest.exception.ErrorMessage;
import com.example.SpringSecondAppTest.exception.IngredientAlreadyExistException;
import com.example.SpringSecondAppTest.exception.IngredientNotFoundException;
import com.example.SpringSecondAppTest.global_user_ingredients_view.GlobalAndUserIngredientsViewRepository;
import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.ingerdient.IngredientRepository;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDtoMapper;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientWithoutIdDto;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user_ingredient.dto.UserIngredientDto;
import com.example.SpringSecondAppTest.user_ingredient.dto.UserIngredientDtoMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserIngredientService {

    private final UserIngredientRepository userIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final GlobalAndUserIngredientsViewRepository globalAndUserIngredientsViewRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public List<IngredientDto> findIngredientsForUser(User user) {
        List<UserIngredient> ingredients = userIngredientRepository.findAllForUserByUserLogin(user.getUsername());
        return UserIngredientDtoMapper.mapToIngredientsMealsDtos(ingredients);
    }

    public List<IngredientDto> findIngredientsForUserWithMainIngredients(User user) {
        List<UserIngredient> userIngredients = userIngredientRepository.findAllForUserByUserLogin(user.getUsername());
        List<Ingredient> ingredients = ingredientRepository.findAll();
        List<IngredientDto> ingredientDtosFromMain = IngredientDtoMapper.mapToIngredientDtos(ingredients);
        List<IngredientDto> ingredientDtosFromUser = UserIngredientDtoMapper.mapToIngredientsMealsDtos(userIngredients);
        return Stream.concat(ingredientDtosFromMain.stream(), ingredientDtosFromUser.stream()).toList();
    }

    public UserIngredientDto createIngredientForUser(User user, IngredientWithoutIdDto newIngredient) {
        List<UserIngredient> ingredientsForLoggedUser = userIngredientRepository.findAllForUserByUserLogin(user.getUsername());
        if (ingredientsForLoggedUser.stream().anyMatch(ingredient -> ingredient.getName().equalsIgnoreCase(newIngredient.name()))
                || ingredientRepository.findByName(newIngredient.name()).isPresent()) {
            throw new IngredientAlreadyExistException(ErrorMessage.INGREDIENT_ALREADY_EXIST);
        }
        return UserIngredientDtoMapper.mapToUserIngredientDto(
                userIngredientRepository.save(UserIngredientDtoMapper.mapToUserIngredient(newIngredient, user)));
    }

    public UserIngredientDto updateIngredientForUser(User user, Long ingredientId, IngredientWithoutIdDto userIngredient) {
        List<UserIngredient> ingredients = userIngredientRepository.findAllForUserByUserLogin(user.getUsername());
        if (ingredientRepository.findByName(userIngredient.name()).isPresent()) {
            throw new IngredientAlreadyExistException(ErrorMessage.INGREDIENT_ALREADY_EXIST);
        }
        if (ingredients.stream()
                .anyMatch(ingredient -> ingredient.getName().equals(userIngredient.name()) &&
                        !ingredient.getId().equals(ingredientId))) {
            throw new IngredientAlreadyExistException(ErrorMessage.INGREDIENT_ALREADY_EXIST);
        } else {
            return UserIngredientDtoMapper.mapToUserIngredientDto(
                    userIngredientRepository.findById(ingredientId)
                            .map(ingredient -> {
                                ingredient.setName(userIngredient.name());
                                ingredient.setIngredientCategory(userIngredient.ingredientCategory());
                                return userIngredientRepository.save(ingredient);
                            })
                            .orElseThrow(() -> new IngredientNotFoundException(String.format(ErrorMessage.INGREDIENT_NOT_FOUND, ingredientId))));
        }
    }

    public void deleteIngredientForUser(User user, Long ingredientId) {
        List<UserIngredient> ingredients = userIngredientRepository.findAllForUserByUserLogin(user.getUsername());
        if (ingredients.stream()
                .anyMatch(ingredient -> ingredient.getId().equals(ingredientId))) {
            userIngredientRepository.deleteById(ingredientId);
        }
    }
}
