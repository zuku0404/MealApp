package com.example.SpringSecondAppTest.ingerdient.dto;

import com.example.SpringSecondAppTest.ingerdient.IngredientCategory;

public record IngredientWithoutIdDto(
        String name,
        IngredientCategory ingredientCategory) {
}