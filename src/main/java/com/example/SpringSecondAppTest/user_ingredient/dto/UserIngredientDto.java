package com.example.SpringSecondAppTest.user_ingredient.dto;

import com.example.SpringSecondAppTest.ingerdient.IngredientCategory;
import com.example.SpringSecondAppTest.user.User;

public record UserIngredientDto (
        Long id,
        String name,
        IngredientCategory ingredientCategory,
        User user) {
}
