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

import java.util.List;
import java.util.Objects;

public class SampleHelper {
    private SampleHelper() {
    }

    public static Meal getMealById(Long id, List<Meal> meals) {
        return meals.stream()
                .filter(meal -> meal.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Meal not found with id: " + id));
    }

    public static Meal getMealById(Long id) {
        return getMealById(id, MealSample.MEALS);
    }

    public static FamilyMeal getFamilyMealById(Long id, List<FamilyMeal> familyMeals) {
        return familyMeals.stream()
                .filter(familyMeal -> familyMeal.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("FamilyMeal not found with id: " + id));
    }

    public static FamilyMeal getFamilyMealById(Long id) {
        return getFamilyMealById(id, FamilyMealsSample.FAMILY_MEALS);
    }

    public static Ingredient getIngredientByName(String name, List<Ingredient> ingredients) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found with name: " + name));
    }

    public static Ingredient getIngredientByName(String name) {
        return getIngredientByName(name, IngredientSample.INGREDIENTS);
    }

    public static FamilyIngredient getFamilyIngredientByName(String name, List<FamilyIngredient> familyIngredients) {
        return familyIngredients.stream()
                .filter(familyIngredient -> familyIngredient.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found with name: " + name));
    }

    public static FamilyIngredient getFamilyIngredientByName(String name) {
        return getFamilyIngredientByName(name, FamilyIngredientSample.FAMILY_INGREDIENTS);
    }

    public static Family getFamilyById(Long id, List<Family> families) {
        return families.stream()
                .filter(family -> Objects.equals(family.getId(), id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Family not found"));
    }

    public static Family getFamilyById(Long id) {
        return getFamilyById(id, FamilySample.FAMILIES);
    }

    public static Cuisine getCuisineByCuisineType(CuisineType type, List<Cuisine> cuisines) {
        return cuisines.stream()
                .filter(cuisine -> cuisine.getCuisineType().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cuisine not found with type: " + type.name()));
    }

    public static Cuisine getCuisineByCuisineType(CuisineType type) {
        return getCuisineByCuisineType(type, CuisineSample.CUISINE);
    }
}
