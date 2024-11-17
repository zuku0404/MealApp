package com.example.SpringSecondAppTest.user_meal.dto;

import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.user_ingredient_meal.dto.UserIngredientMealDtoMapper;
import com.example.SpringSecondAppTest.user_meal.UserMeal;

import java.util.List;

public class UserMealDtoMapper {
    private UserMealDtoMapper(){}

    public static List<DetailedUserMealDto> mapToDetailedMealDtos(List<UserMeal> userMeals) {
        return userMeals.stream()
                .map(UserMealDtoMapper::mapToDetailedMealDto)
                .toList();
    }

    public static DetailedUserMealDto mapToDetailedMealDto(UserMeal meal) {
        return new DetailedUserMealDto(
                meal.getId(),
                meal.getName(),
                meal.getDescription(),
                meal.getCuisine().getCuisineType(),
                UserIngredientMealDtoMapper.mapToUserIngredientMealDtos(meal.getUserIngredientMeals()));
    }

    public static List<BasicMealDto> mapToBasicMealDtos(List<UserMeal> userMeals) {
        return userMeals.stream()
                .map(UserMealDtoMapper::mapToBasicMealDto)
                .toList();
    }

    public static BasicMealDto mapToBasicMealDto(UserMeal meal) {
        return new BasicMealDto(
                meal.getId(),
                meal.getName());
    }
}
