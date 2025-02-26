package com.example.SpringSecondAppTest.meal.dto;


import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.meal_composition.dto.MealCompositionDto;
import com.example.SpringSecondAppTest.views.Source;

import java.util.Set;

public record DetailedMealDto(
        Long id,
        String name,
        String description,
        String imageUrl,
        Source source,
        CuisineType cuisineType,
        Set<MealCompositionDto> mealCompositions
) {
}
