package com.example.SpringSecondAppTest.family.dto;

import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDto;
import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.user_family.dto.UserFamilyWithoutFamilyDto;

import java.util.List;

public record FamilyDto(
        Long id,
        String name,
        List<IngredientDto> familyIngredients,
        List<BasicMealDto> familyMeals,
        List<UserFamilyWithoutFamilyDto> userFamily) {
}
