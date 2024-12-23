package com.example.SpringSecondAppTest.sample;

import com.example.SpringSecondAppTest.cuisine.Cuisine;
import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.family_ingredient.FamilyIngredient;
import com.example.SpringSecondAppTest.family_meal.FamilyMeal;
import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.meal.Meal;
import com.example.SpringSecondAppTest.sample.family_meal.FamilyIngredientSample;
import com.example.SpringSecondAppTest.sample.family_meal.FamilyMealsSample;
import com.example.SpringSecondAppTest.sample.meal.IngredientSample;
import com.example.SpringSecondAppTest.sample.meal.MealSample;
import com.example.SpringSecondAppTest.sample.user.FamilySample;

import java.util.Objects;

public class SampleHelper {
    private SampleHelper(){}

    public static FamilyMeal getFamilyMealById(Long id) {
        return FamilyMealsSample.FAMILY_MEALS.stream()
                .filter(familyMeal -> familyMeal.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("FamilyMeal not found with id: " + id));
    }

    public static Ingredient getIngredientByName(String name) {
        return IngredientSample.INGREDIENTS.stream()
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found with name: " + name));
    }

    public static FamilyIngredient getFamilyIngredientByName(String name) {
        return FamilyIngredientSample.FAMILY_INGREDIENTS.stream()
                .filter(familyIngredient -> familyIngredient.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found with name: " + name));
    }

    public static Family getFamilyById(Long id) {
        return FamilySample.FAMILIES.stream()
                .filter(family -> Objects.equals(family.getId(), id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Family not found"));
    }

    public static Cuisine getCuisineByCuisineType(CuisineType type) {
        return CuisineSample.CUISINES.stream()
                .filter(cuisine -> cuisine.getCuisineType().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cuisine not found with type: " + type.name()));
    }

    public static Meal getMealById(Long id) {
        return MealSample.MEALS.stream()
                .filter(meal -> meal.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Meal not found with id: " + id));
    }
}
