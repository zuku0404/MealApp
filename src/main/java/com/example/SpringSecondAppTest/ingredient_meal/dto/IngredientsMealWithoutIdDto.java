package com.example.SpringSecondAppTest.ingredient_meal.dto;

import com.example.SpringSecondAppTest.ingredient_meal.Unit;

public record IngredientsMealWithoutIdDto(
        Double count,
        Unit unit,
        String ingredientName) {
}
