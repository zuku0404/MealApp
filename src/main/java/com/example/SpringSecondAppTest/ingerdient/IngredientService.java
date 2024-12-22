package com.example.SpringSecondAppTest.ingerdient;

import com.example.SpringSecondAppTest.exception.ErrorMessage;
import com.example.SpringSecondAppTest.exception.custom.IngredientAlreadyExistException;
import com.example.SpringSecondAppTest.exception.custom.IngredientNotFoundException;
import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDtoMapper;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientWithoutIdDto;
import com.example.SpringSecondAppTest.family_ingredient.FamilyIngredient;
import com.example.SpringSecondAppTest.family_ingredient.FamilyIngredientRepository;
import com.example.SpringSecondAppTest.family_composition.FamilyMealComposition;
import com.example.SpringSecondAppTest.family_composition.FamilyMealCompositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final FamilyMealCompositionRepository familyMealCompositionRepository;
    private final FamilyIngredientRepository familyIngredientRepository;

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
            familyMealCompositionRepository.findFamilyIdsForIngredientId(id)
                    .forEach(user -> {
                        FamilyIngredient newFamilyIngredient = IngredientDtoMapper.mapToUserIngredient(ingredient);
                        newFamilyIngredient.setFamily(user);
                        List<FamilyMealComposition> familyMealCompositions = familyMealCompositionRepository.findByIngredientIdAndUserId(ingredient.getId(), user.getId());
                        familyMealCompositions.forEach(userIngredientMeal -> {
                            userIngredientMeal.setIngredient(null);
                            userIngredientMeal.setFamilyIngredient(newFamilyIngredient);
                        });
                        newFamilyIngredient.setIngredientMeals(familyMealCompositions);
                        familyIngredientRepository.save(newFamilyIngredient);
                    });
            ingredientRepository.deleteById(id);
        });
    }


    private void findAndReplaceIngredientName(String name) {
        if (ingredientRepository.findByName(name).isPresent()) {
            throw new IngredientAlreadyExistException(ErrorMessage.INGREDIENT_ALREADY_EXIST);
        }
        familyIngredientRepository.findByName(name).forEach(ingredient -> {
                    Family family = ingredient.getFamily();
                    String updatedIngredientName = name + "_" + family.getName();
                    int iterator = 1;
                    Long familyIdContainUpdatedIngredientName = family.getId();
                    while (familyIngredientRepository.findByFamilyIdAndName(familyIdContainUpdatedIngredientName, updatedIngredientName).isPresent()) {
                        updatedIngredientName += iterator++;
                    }
                    ingredient.setName(updatedIngredientName);
                }
        );
    }
}
