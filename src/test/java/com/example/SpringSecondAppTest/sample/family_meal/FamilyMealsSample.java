package com.example.SpringSecondAppTest.sample.family_meal;

import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.family_ingredient.FamilyIngredient;
import com.example.SpringSecondAppTest.family_meal.FamilyMeal;
import com.example.SpringSecondAppTest.sample.user.FamilySample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.SpringSecondAppTest.sample.SampleHelper.getCuisineByCuisineType;
import static com.example.SpringSecondAppTest.sample.SampleHelper.getFamilyById;

public class FamilyMealsSample {
    private FamilyMealsSample(){}

    public static final List<FamilyMeal> FAMILY_MEALS;

    static {
        FAMILY_MEALS = Collections.unmodifiableList(create());
    }

    public static List<FamilyMeal> create(){
        List<Family> families = FamilySample.create();
        return new ArrayList<>(Arrays.asList(
                new FamilyMeal(1L, "Tofu Stir-Fry", "Stir-fried tofu with vegetables and soy sauce",
                        "http://localhost:8080/images/default.jpg", getCuisineByCuisineType(CuisineType.ITALIAN),
                        getFamilyById(2L, families)),
                new FamilyMeal(2L, "Spicy Veggie Wrap", "A wrap with vegetables and spicy tofu",
                        "http://localhost:8080/images/default.jpg", getCuisineByCuisineType(CuisineType.ITALIAN),
                        getFamilyById(2L, families)),
                new FamilyMeal(2L, "Chili Tofu Salad", "Fresh salad with chili tofu and greens",
                        "http://localhost:8080/images/default.jpg", getCuisineByCuisineType(CuisineType.ITALIAN),
                        getFamilyById(2L, families))
        ));
    }
}
