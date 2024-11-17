package com.example.SpringSecondAppTest.user_ingredient_meal.dto;

import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;
import com.example.SpringSecondAppTest.ingredient_meal.Unit;

public record UserIngredientMealDto(
        Long id,
        Double count,
        Unit unit,
        IngredientDto ingredientDto) {
}