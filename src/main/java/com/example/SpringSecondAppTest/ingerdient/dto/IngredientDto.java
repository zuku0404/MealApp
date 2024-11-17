package com.example.SpringSecondAppTest.ingerdient.dto;

import com.example.SpringSecondAppTest.ingerdient.IngredientCategory;

public record IngredientDto (
        Long id,
        String name,
        IngredientCategory ingredientCategory) {
}
