package com.example.SpringSecondAppTest.family_ingredient.dto;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.ingerdient.IngredientCategory;
import com.example.SpringSecondAppTest.user.User;

public record FamilyIngredientDto(
        Long id,
        String name,
        IngredientCategory ingredientCategory,
        Family family) {
}
