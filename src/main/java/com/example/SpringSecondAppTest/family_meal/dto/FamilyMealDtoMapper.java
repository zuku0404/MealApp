package com.example.SpringSecondAppTest.family_meal.dto;

import com.example.SpringSecondAppTest.family_composition.dto.FamilyIngredientMealDtoMapper;
import com.example.SpringSecondAppTest.family_meal.FamilyMeal;
import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealDto;

import java.util.List;

public class FamilyMealDtoMapper {
    private FamilyMealDtoMapper(){}

    public static List<DetailedMealDto> mapToDetailedMealDtos(List<FamilyMeal> familyMeals) {
        return familyMeals.stream()
                .map(FamilyMealDtoMapper::mapToDetailedMealDto)
                .toList();
    }

    public static DetailedMealDto mapToDetailedMealDto(FamilyMeal meal) {
        return new DetailedMealDto (
                meal.getId(),
                meal.getName(),
                meal.getDescription(),
                meal.getImageUrl(),
                meal.getCuisine().getCuisineType(),
                FamilyIngredientMealDtoMapper.mapToFamilyIngredientMealDtos(meal.getFamilyMealCompositions()));
    }

    public static List<BasicMealDto> mapToBasicMealDtos(List<FamilyMeal> familyMeals) {
        return familyMeals.stream()
                .map(FamilyMealDtoMapper::mapToBasicMealDto)
                .toList();
    }

    public static BasicMealDto mapToBasicMealDto(FamilyMeal meal) {
        return new BasicMealDto(
                meal.getId(),
                meal.getName(),
                meal.getImageUrl());
    }
}
