package com.example.SpringSecondAppTest.ingerdient.dto;

import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.family_ingredient.FamilyIngredient;

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
                ingredient.getSource(),
                ingredient.getIngredientCategory());
    }

    public static Ingredient mapToIngredient(IngredientWithoutIdDto ingredientDto) {
        return new Ingredient(
                ingredientDto.name(),
                ingredientDto.ingredientCategory());
    }

    public static FamilyIngredient mapToUserIngredient(Ingredient ingredient) {
        return new FamilyIngredient(
                ingredient.getName(),
                ingredient.getIngredientCategory());
    }
}