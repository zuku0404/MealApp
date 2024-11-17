package com.example.SpringSecondAppTest.user_meal.dto;

import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.user_ingredient_meal.dto.UserIngredientMealDto;

import java.util.Set;

public record DetailedUserMealDto(
        Long id,
        String name,
        String description,
        CuisineType cuisineType,
        Set<UserIngredientMealDto> ingredientMeals) {
}
