package com.example.SpringSecondAppTest.ingredient_meal.dto;

import com.example.SpringSecondAppTest.ingredient_meal.Unit;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;

public record IngredientsMealsDto(
        Long id,
        Double count,
        Unit unit,
        IngredientDto ingredientDto) {
}
