package com.example.SpringSecondAppTest.meal.dto;


import com.example.SpringSecondAppTest.ingredient_meal.dto.IngredientsMealsDtoMapper;
import com.example.SpringSecondAppTest.meal.Meal;

import java.util.List;

public class MealDtoMapper {
    private MealDtoMapper() {
    }

    public static List<BasicMealDto> mapToBasicMealDtos(List<Meal> meals){
        return meals.stream()
                .map(MealDtoMapper::mapToBasicMealDto)
                .toList();
    }

    private static BasicMealDto mapToBasicMealDto(Meal meal) {
        return new BasicMealDto(
                meal.getId(),
                meal.getName());
    }

    public static List<DetailedMealDto> mapToDetailedMealDtos(List<Meal> meals){
        return meals.stream()
                .map(MealDtoMapper::mapToDetailedMealDto)
                .toList();
    }

    public static DetailedMealDto mapToDetailedMealDto(Meal meal) {
        return new DetailedMealDto(
                meal.getId(),
                meal.getName(),
                meal.getDescription(),
                meal.getCuisine().getCuisineType(),
                IngredientsMealsDtoMapper.mapToIngredientMealDtos(meal.getIngredientMeals()));
    }
}
