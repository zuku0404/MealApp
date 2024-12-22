package com.example.SpringSecondAppTest.meal_composition.dto;

import com.example.SpringSecondAppTest.meal_composition.Unit;

public record MealCompositionWithoutIdDto(
        Double count,
        Unit unit,
        String ingredientName) {
}
