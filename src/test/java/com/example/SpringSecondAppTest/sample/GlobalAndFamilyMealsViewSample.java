package com.example.SpringSecondAppTest.sample;

import com.example.SpringSecondAppTest.family_meal.FamilyMeal;
import com.example.SpringSecondAppTest.meal.Meal;
import com.example.SpringSecondAppTest.sample.family_meal.FamilyMealsSample;
import com.example.SpringSecondAppTest.sample.meal.MealSample;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsView;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsViewId;

import java.util.List;
import java.util.stream.Stream;

public class GlobalAndFamilyMealsViewSample {

    public static List<GlobalAndFamilyMealsView> create() {
        List<FamilyMeal> familyMeals = FamilyMealsSample.create();
        List<Meal> meals = MealSample.create();

        return Stream.concat(
                        meals.stream()
                                .map(meal -> {
                                    GlobalAndFamilyMealsView view = new GlobalAndFamilyMealsView();
                                    view.setId(new GlobalAndFamilyMealsViewId(meal.getId(), meal.getSource()));
                                    view.setName(meal.getName());
                                    view.setDescription(meal.getDescription());
                                    view.setImageUrl(meal.getImageUrl());
                                    view.setFamilyId(null);
                                    return view;
                                }),
                        familyMeals.stream()
                                .map(familyMeal -> {
                                    GlobalAndFamilyMealsView view = new GlobalAndFamilyMealsView();
                                    view.setId(new GlobalAndFamilyMealsViewId(familyMeal.getId(), familyMeal.getSource()));
                                    view.setName(familyMeal.getName());
                                    view.setDescription(familyMeal.getDescription());
                                    view.setImageUrl(familyMeal.getImageUrl());
                                    view.setFamilyId(familyMeal.getFamily().getId());
                                    return view;
                                }))
                .toList();
    }
}
