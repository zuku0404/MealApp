package com.example.SpringSecondAppTest.user_ingredient_meal.dto;

import com.example.SpringSecondAppTest.ingredient_meal.Unit;

public record UserIngredientsMealWithoutIdDto (
        Double count,
        Unit unit,
        Long ingredientId) {
}