package com.example.SpringSecondAppTest.user_meal.dto;

import com.example.SpringSecondAppTest.user_ingredient_meal.dto.UserIngredientsMealWithoutIdDto;

import java.util.List;

public record DetailedUserMealWithoutIdDto(
        String name,
        String description,
        Long cuisineTypeId,
        List<UserIngredientsMealWithoutIdDto> ingredientMeals){
}
