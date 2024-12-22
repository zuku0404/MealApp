package com.example.SpringSecondAppTest.meal.dto;

import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.meal_composition.dto.MealCompositionWithoutIdDto;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record DetailedMealWithoutIdDto(
        @NotBlank
        String name,
        String description,
        String imageUrl,
        @NotBlank
        CuisineType cuisineType,
        Set<MealCompositionWithoutIdDto> mealCompositions) {
}
