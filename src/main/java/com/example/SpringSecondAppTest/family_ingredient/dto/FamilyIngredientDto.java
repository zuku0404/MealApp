package com.example.SpringSecondAppTest.family_ingredient.dto;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.ingerdient.IngredientCategory;

public record FamilyIngredientDto(
        Long id,
        String name,
        IngredientCategory ingredientCategory,
        Family family) {
}
