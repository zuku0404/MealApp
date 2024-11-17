package com.example.SpringSecondAppTest.user_ingredient.dto;

import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;
import com.example.SpringSecondAppTest.ingerdient.dto.IngredientWithoutIdDto;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user_ingredient.UserIngredient;

import java.util.List;

public class UserIngredientDtoMapper {
    private UserIngredientDtoMapper() {
    }

    public static List<IngredientDto> mapToIngredientsMealsDtos(List<UserIngredient> ingredients) {
        return ingredients.stream()
                .map(UserIngredientDtoMapper::mapToIngredientsMealsDto)
                .toList();
    }

    public static IngredientDto mapToIngredientsMealsDto(UserIngredient ingredient) {
        return new IngredientDto(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getIngredientCategory());
    }

    public static UserIngredient mapToUserIngredient(IngredientWithoutIdDto ingredient, User user) {
        return new UserIngredient(
                ingredient.name(),
                ingredient.ingredientCategory(),
                user
        );
    }

    public static UserIngredientDto mapToUserIngredientDto(UserIngredient userIngredient) {
        return new UserIngredientDto(
                userIngredient.getId(),
                userIngredient.getName(),
                userIngredient.getIngredientCategory(),
                userIngredient.getUser()
        );
    }

    public static IngredientDto mapToIngredientDto(UserIngredient userIngredient) {
        return new IngredientDto(
                userIngredient.getId(),
                userIngredient.getName(),
                userIngredient.getIngredientCategory());
    }
}
