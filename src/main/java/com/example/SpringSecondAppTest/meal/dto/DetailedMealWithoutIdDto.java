package com.example.SpringSecondAppTest.meal.dto;

import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.ingredient_meal.dto.IngredientsMealWithoutIdDto;

import java.util.Set;

public record DetailedMealWithoutIdDto(
        String name,
        String description,
        CuisineType cuisineType,
        Set<IngredientsMealWithoutIdDto> ingredientMeals) {
}
