package com.example.SpringSecondAppTest.meal_frequency.dto;

import com.example.SpringSecondAppTest.meal_frequency.MealFrequency;

public class MealFrequencyMapper {
    private MealFrequencyMapper(){}

    public static MealFrequencyDto mapToMealFrequencyDto(MealFrequency mealFrequency) {
        return new MealFrequencyDto(
                mealFrequency.getFamily().getId(),
                mealFrequency.getMeal().getId().getMealId(),
                mealFrequency.getMeal().getId().getSource(),
                mealFrequency.getFrequency()
        );
    }
}
