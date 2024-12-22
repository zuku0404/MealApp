package com.example.SpringSecondAppTest.ingerdient.dto;

import com.example.SpringSecondAppTest.ingerdient.IngredientCategory;
import jakarta.validation.constraints.NotBlank;

public record IngredientWithoutIdDto(
        @NotBlank
        String name,
        IngredientCategory ingredientCategory) {
}