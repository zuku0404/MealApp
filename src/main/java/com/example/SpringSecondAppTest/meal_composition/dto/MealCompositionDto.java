package com.example.SpringSecondAppTest.meal_composition.dto;

import com.example.SpringSecondAppTest.meal_composition.Unit;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;

public record MealCompositionDto(
        Long id,
        Double count,
        Unit unit,
        IngredientDto ingredientDto) {
}
