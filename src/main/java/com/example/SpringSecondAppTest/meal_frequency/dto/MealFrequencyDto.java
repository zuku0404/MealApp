package com.example.SpringSecondAppTest.meal_frequency.dto;


import com.example.SpringSecondAppTest.meal_frequency.Frequency;
import com.example.SpringSecondAppTest.views.Source;

public record MealFrequencyDto(
        Long familyId,
        Long mealId,
        Source source,
        Frequency frequency
) {
}
