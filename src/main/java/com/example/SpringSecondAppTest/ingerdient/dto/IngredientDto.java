package com.example.SpringSecondAppTest.ingerdient.dto;

import com.example.SpringSecondAppTest.ingerdient.IngredientCategory;
import com.example.SpringSecondAppTest.views.Source;

public record IngredientDto (
        Long id,
        String name,
        Source source,
        IngredientCategory ingredientCategory) {
}
