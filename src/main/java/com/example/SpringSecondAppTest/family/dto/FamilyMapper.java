package com.example.SpringSecondAppTest.family.dto;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.family_ingredient.dto.FamilyIngredientDtoMapper;
import com.example.SpringSecondAppTest.family_meal.dto.FamilyMealDtoMapper;
import com.example.SpringSecondAppTest.user_family.dto.UserFamilyDtoMapper;

import java.util.List;

public class FamilyMapper {
    private FamilyMapper() {
    }

    public static List<FamilyDto> mapToFamilyDtos(List<Family> family) {
        return family.stream()
                .map(FamilyMapper::mapToFamilyDto)
                .toList();
    }

    public static FamilyDto mapToFamilyDto(Family family) {
        return new FamilyDto(
                family.getId(),
                family.getName(),
                FamilyIngredientDtoMapper.mapToIngredientsMealsDtos(family.getIngredients().stream().toList()),
                FamilyMealDtoMapper.mapToBasicMealDtos(family.getMeals().stream().toList()),
                UserFamilyDtoMapper.mapToUserFamilyWithoutFamilyDtos(family.getFamilies()));
    }

    public static FamilyBasicDto mapToFamilyBasicDto (Family family) {
        return new FamilyBasicDto(
                family.getId(),
                family.getName());
    }
}
