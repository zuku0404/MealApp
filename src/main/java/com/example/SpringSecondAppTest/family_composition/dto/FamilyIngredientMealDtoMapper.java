package com.example.SpringSecondAppTest.family_composition.dto;

import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDtoMapper;
import com.example.SpringSecondAppTest.meal_composition.dto.MealCompositionDto;
import com.example.SpringSecondAppTest.family_ingredient.dto.FamilyIngredientDtoMapper;
import com.example.SpringSecondAppTest.family_composition.FamilyMealComposition;

import java.util.Set;
import java.util.stream.Collectors;

public class FamilyIngredientMealDtoMapper {
    private FamilyIngredientMealDtoMapper() {
    }

    public static Set<MealCompositionDto> mapToFamilyIngredientMealDtos(Set<FamilyMealComposition> ingredientMeals) {
        return ingredientMeals.stream()
                .map(FamilyIngredientMealDtoMapper::mapToFamilyIngredientsMealDto)
                .collect(Collectors.toSet());
    }

    public static MealCompositionDto mapToFamilyIngredientsMealDto(FamilyMealComposition ingredientMeal) {
        return new MealCompositionDto(
                ingredientMeal.getId(),
                ingredientMeal.getCount(),
                ingredientMeal.getUnit(),
                ingredientMeal.getIngredient() != null ? IngredientDtoMapper.mapToIngredientDto(ingredientMeal.getIngredient()) :
                        FamilyIngredientDtoMapper.mapToIngredientDto(ingredientMeal.getFamilyIngredient()));
    }
}
