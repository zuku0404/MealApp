package com.example.SpringSecondAppTest.sample.family_meal;

import com.example.SpringSecondAppTest.family_composition.FamilyMealComposition;
import com.example.SpringSecondAppTest.meal_composition.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.SpringSecondAppTest.sample.SampleHelper.*;

public class FamilyMealCompositionSample {
    private FamilyMealCompositionSample(){}

    public static final List<FamilyMealComposition> FAMILY_MEAL_COMPOSITIONS = new ArrayList<>(Arrays.asList(
            new FamilyMealComposition(1L, 200.0, Unit.GRAM, getFamilyMealById(1L),
                    getFamilyIngredientByName("Tofu"), getIngredientByName(null)),
            new FamilyMealComposition(1L, 2.0, Unit.CLOVE, getFamilyMealById(1L),
                    getFamilyIngredientByName(null), getIngredientByName("Garlic")),
            new FamilyMealComposition(1L, 2.0, Unit.TABLESPOON, getFamilyMealById(1L),
                    getFamilyIngredientByName(null), getIngredientByName("Olive oil")),
            new FamilyMealComposition(1L, 1.0, Unit.PIECE, getFamilyMealById(1L),
                    getFamilyIngredientByName(null), getIngredientByName("Onion")),
            new FamilyMealComposition(1L, 50.0, Unit.MILLILITER, getFamilyMealById(1L),
                    getFamilyIngredientByName("Soy sauce"), getIngredientByName(null)),

// Family Meal 2
            new FamilyMealComposition(2L, 150.0, Unit.GRAM, getFamilyMealById(2L),
                    getFamilyIngredientByName("Tofu"), getIngredientByName(null)),
            new FamilyMealComposition(2L, 1.0, Unit.PIECE, getFamilyMealById(2L),
                    getFamilyIngredientByName("Chili Pepper"), getIngredientByName(null)),
            new FamilyMealComposition(2L, 1.0, Unit.PIECE, getFamilyMealById(2L),
                    getFamilyIngredientByName(null), getIngredientByName("Tomato")),
            new FamilyMealComposition(2L, 50.0, Unit.GRAM, getFamilyMealById(2L),
                    getFamilyIngredientByName(null), getIngredientByName("Lettuce")),
            new FamilyMealComposition(2L, 1.0, Unit.PIECE, getFamilyMealById(2L),
                    getFamilyIngredientByName(null), getIngredientByName("Tortilla")),
            new FamilyMealComposition(2L, 1.0, Unit.TABLESPOON, getFamilyMealById(2L),
                    getFamilyIngredientByName(null), getIngredientByName("Olive oil"))
    ));
}