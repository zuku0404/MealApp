package com.example.SpringSecondAppTest.ingredient_meal.dto;


import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDtoMapper;
import com.example.SpringSecondAppTest.ingredient_meal.IngredientMeal;

import java.util.List;

public class IngredientsMealsDtoMapper {
    private IngredientsMealsDtoMapper() {
    }

    public static List<IngredientsMealsDto> mapToIngredientMealDtos(List<IngredientMeal> ingredientMeals) {
        return ingredientMeals.stream()
                .map(IngredientsMealsDtoMapper::mapToIngredientsMealDto)
                .toList();
    }

    public static IngredientsMealsDto mapToIngredientsMealDto(IngredientMeal ingredientMeal) {
        return new IngredientsMealsDto(
                ingredientMeal.getId(),
                ingredientMeal.getCount(),
                ingredientMeal.getUnit(),
                IngredientDtoMapper.mapToIngredientDto(ingredientMeal.getIngredient()));
    }
}