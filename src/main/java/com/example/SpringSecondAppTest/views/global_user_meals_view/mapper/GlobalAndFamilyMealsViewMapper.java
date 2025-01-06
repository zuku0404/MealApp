package com.example.SpringSecondAppTest.views.global_user_meals_view.mapper;

import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsView;

import java.util.List;

public class GlobalAndFamilyMealsViewMapper {
    private GlobalAndFamilyMealsViewMapper(){}

    public static List<BasicMealDto> mapToBasicMealDtos(List<GlobalAndFamilyMealsView> globalAndFamilyMealsViews){
        return globalAndFamilyMealsViews.stream()
                .map(GlobalAndFamilyMealsViewMapper::mapToBasicMealDto)
                .toList();
    }

    public static BasicMealDto mapToBasicMealDto(GlobalAndFamilyMealsView globalAndFamilyMeal){
        return new BasicMealDto(
                globalAndFamilyMeal.getId().getMealId(),
                globalAndFamilyMeal.getId().getSource(),
                globalAndFamilyMeal.getName(),
                globalAndFamilyMeal.getImageUrl()
        );
    }
}
