package com.example.SpringSecondAppTest.family_ingredient.dto;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.family_ingredient.FamilyIngredient;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientWithoutIdDto;

import java.util.List;

public class FamilyIngredientDtoMapper {
    private FamilyIngredientDtoMapper() {
    }

    public static List<IngredientDto> mapToIngredientsMealsDtos(List<FamilyIngredient> ingredients) {
        return ingredients.stream()
                .map(FamilyIngredientDtoMapper::mapToIngredientsMealsDto)
                .toList();
    }

    public static IngredientDto mapToIngredientsMealsDto(FamilyIngredient ingredient) {
        return new IngredientDto(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getSource(),
                ingredient.getIngredientCategory());
    }

    public static FamilyIngredient mapToFamilyIngredient(IngredientWithoutIdDto ingredient, Family family) {
        return new FamilyIngredient(
                ingredient.name(),
                ingredient.ingredientCategory(),
                family
        );
    }

    public static FamilyIngredientDto mapToUserIngredientDto(FamilyIngredient familyIngredient) {
        return new FamilyIngredientDto(
                familyIngredient.getId(),
                familyIngredient.getName(),
                familyIngredient.getIngredientCategory(),
                familyIngredient.getFamily()
        );
    }

    public static IngredientDto mapToIngredientDto(FamilyIngredient familyIngredient) {
        return new IngredientDto(
                familyIngredient.getId(),
                familyIngredient.getName(),
                familyIngredient.getSource(),
                familyIngredient.getIngredientCategory());
    }
}
