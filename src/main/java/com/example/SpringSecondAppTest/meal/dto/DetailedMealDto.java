package com.example.SpringSecondAppTest.meal.dto;


import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.ingredient_meal.dto.IngredientsMealsDto;

import java.util.List;

public record DetailedMealDto(
        Long id,
        String name,
        String description,
        CuisineType cuisineType,
        List<IngredientsMealsDto> ingredientMeals){
}
