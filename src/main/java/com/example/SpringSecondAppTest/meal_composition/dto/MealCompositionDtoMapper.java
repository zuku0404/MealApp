package com.example.SpringSecondAppTest.meal_composition.dto;

import com.example.SpringSecondAppTest.ingerdient.dto.IngredientDtoMapper;
import com.example.SpringSecondAppTest.meal_composition.MealComposition;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MealCompositionDtoMapper {
    private MealCompositionDtoMapper() {
    }

    public static Set<MealCompositionDto> mapToMealCompositionDtos(Set<MealComposition> mealCompositions) {
        return mealCompositions.stream()
                .map(MealCompositionDtoMapper::mapToMealCompositionDto)
                .collect(Collectors.toSet());
    }

    public static MealCompositionDto mapToMealCompositionDto(MealComposition mealComposition) {
        return new MealCompositionDto(
                mealComposition.getId(),
                mealComposition.getCount(),
                mealComposition.getUnit(),
                IngredientDtoMapper.mapToIngredientDto(mealComposition.getIngredient()));
    }
}