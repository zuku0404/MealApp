package com.example.SpringSecondAppTest.user_ingredient_meal.dto;

import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDtoMapper;
import com.example.SpringSecondAppTest.user_ingredient.dto.UserIngredientDtoMapper;
import com.example.SpringSecondAppTest.user_ingredient_meal.UserIngredientMeal;

import java.util.Set;
import java.util.stream.Collectors;

public class UserIngredientMealDtoMapper {
    private UserIngredientMealDtoMapper() {
    }

    public static Set<UserIngredientMealDto> mapToUserIngredientMealDtos(Set<UserIngredientMeal> ingredientMeals) {
        return ingredientMeals.stream()
                .map(UserIngredientMealDtoMapper::mapToUserIngredientsMealDto)
                .collect(Collectors.toSet());
    }

    public static UserIngredientMealDto mapToUserIngredientsMealDto(UserIngredientMeal ingredientMeal) {
        return new UserIngredientMealDto(
                ingredientMeal.getId(),
                ingredientMeal.getCount(),
                ingredientMeal.getUnit(),
                ingredientMeal.getIngredient() != null ? IngredientDtoMapper.mapToIngredientDto(ingredientMeal.getIngredient()) :
                        UserIngredientDtoMapper.mapToIngredientDto(ingredientMeal.getUserIngredient()));
    }
}
