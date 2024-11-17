package com.example.SpringSecondAppTest.ingerdient.dto;

import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user_ingredient.UserIngredient;

import java.util.List;

public class IngredientDtoMapper {

    private IngredientDtoMapper(){}

    public static List<IngredientDto> mapToIngredientDtos(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(IngredientDtoMapper::mapToIngredientDto)
                .toList();
    }

    public static IngredientDto mapToIngredientDto(Ingredient ingredient) {
        return new IngredientDto(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getIngredientCategory());
    }

    public static Ingredient mapToIngredient(IngredientWithoutIdDto ingredientDto) {
        return new Ingredient(
                ingredientDto.name(),
                ingredientDto.ingredientCategory());
    }

    public static UserIngredient mapToUserIngredient(Ingredient ingredient) {
        return new UserIngredient(
                ingredient.getName(),
                ingredient.getIngredientCategory());
    }
}