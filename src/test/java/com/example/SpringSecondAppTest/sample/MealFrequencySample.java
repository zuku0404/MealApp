package com.example.SpringSecondAppTest.sample;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.meal_frequency.Frequency;
import com.example.SpringSecondAppTest.meal_frequency.MealFrequency;
import com.example.SpringSecondAppTest.sample.user.FamilySample;
import com.example.SpringSecondAppTest.views.Source;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MealFrequencySample {

    public static List<MealFrequency> create() {
        List<GlobalAndFamilyMealsView> mealsViews = GlobalAndFamilyMealsViewSample.create();
        List<Family> families = FamilySample.create();
        return new ArrayList<>(List.of(
                new MealFrequency(families.get(0), findSpecialView(1L, Source.GLOBAL, mealsViews), Frequency.TWICE_MONTH),
                new MealFrequency(families.get(0), findSpecialView(2L, Source.GLOBAL, mealsViews), Frequency.ONCE_WEEK),
                new MealFrequency(families.get(1), findSpecialView(1L, Source.CUSTOM, mealsViews), Frequency.ONCE_WEEK),
                new MealFrequency(families.get(1), findSpecialView(2L, Source.CUSTOM, mealsViews), Frequency.TWICE_WEEK),
                new MealFrequency(families.get(1), findSpecialView(1L, Source.GLOBAL, mealsViews), Frequency.ONCE_WEEK),
                new MealFrequency(families.get(1), findSpecialView(2L, Source.GLOBAL, mealsViews), Frequency.TWICE_MONTH)));
    }

    private static GlobalAndFamilyMealsView findSpecialView(Long mealId, Source source, List<GlobalAndFamilyMealsView> globalAndFamilyMealsViews) {
        return globalAndFamilyMealsViews.stream()
                .filter(view -> Objects.equals(view.getId().getMealId(), mealId) && view.getId().getSource() == source)
                .findFirst()
                .orElseThrow();
    }
}