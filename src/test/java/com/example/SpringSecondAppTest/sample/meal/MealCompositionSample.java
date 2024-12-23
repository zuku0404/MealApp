package com.example.SpringSecondAppTest.sample.meal;

import com.example.SpringSecondAppTest.meal_composition.MealComposition;
import com.example.SpringSecondAppTest.meal_composition.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.SpringSecondAppTest.sample.SampleHelper.getIngredientByName;
import static com.example.SpringSecondAppTest.sample.SampleHelper.getMealById;

public class MealCompositionSample {
    private MealCompositionSample(){}

    public static final List<MealComposition> MEAL_COMPOSITIONS = new ArrayList<>(Arrays.asList(

            //meal 1
            new MealComposition(1L, 3.0, Unit.PIECE, getMealById(1L), getIngredientByName("Tomato")),
            new MealComposition(2L, 250.0, Unit.GRAM, getMealById(1L), getIngredientByName("Mozzarella")),
            new MealComposition(3L, 500.0, Unit.GRAM, getMealById(1L), getIngredientByName("Flour")),
            new MealComposition(4L, 1.0, Unit.PIECE, getMealById(1L), getIngredientByName("Yeast")),
            new MealComposition(5L, 1.0, Unit.TABLESPOON, getMealById(1L), getIngredientByName("Olive oil")),
            new MealComposition(6L, 1.0, Unit.TEASPOON, getMealById(1L), getIngredientByName("Salt")),
            new MealComposition(7L, 200.0, Unit.MILLILITER, getMealById(1L), getIngredientByName("Water")),

            //meal2
            new MealComposition(9L, 400.0, Unit.GRAM, getMealById(2L), getIngredientByName("Spaghetti")),
            new MealComposition(10L, 150.0, Unit.GRAM, getMealById(2L), getIngredientByName("Bacon")),
            new MealComposition(11L, 100.0, Unit.GRAM, getMealById(2L), getIngredientByName("Pecorino/Parmesan cheese")),
            new MealComposition(12L, 4.0, Unit.PIECE, getMealById(2L), getIngredientByName("Eggs")),
            new MealComposition(13L, 2.0, Unit.CLOVE, getMealById(2L), getIngredientByName("Garlic")),
            new MealComposition(14L, 1.0, Unit.TEASPOON, getMealById(2L), getIngredientByName("Black pepper")),
            new MealComposition(15L, 2.0, Unit.TABLESPOON, getMealById(2L), getIngredientByName("Olive oil")),

            // meal 3
            new MealComposition(15L, 500.0, Unit.GRAM, getMealById(3L), getIngredientByName("Flour")),
            new MealComposition(16L, 250.0, Unit.MILLILITER, getMealById(3L), getIngredientByName("Water")),
            new MealComposition(17L, 1.0, Unit.PIECE, getMealById(3L), getIngredientByName("Egg")),
            new MealComposition(18L, 1.0, Unit.TEASPOON, getMealById(3L), getIngredientByName("Salt")),
            new MealComposition(19L, 500.0, Unit.GRAM, getMealById(3L), getIngredientByName("Potatoes")),
            new MealComposition(20L, 300.0, Unit.GRAM, getMealById(3L), getIngredientByName("Cottage cheese")),
            new MealComposition(21L, 1.0, Unit.PIECE, getMealById(3L), getIngredientByName("Onion")),
            new MealComposition(22L, 2.0, Unit.TABLESPOON, getMealById(3L), getIngredientByName("Butter")),

            //meal 4
            new MealComposition(23L, 1.0, Unit.KILOGRAM, getMealById(4L), getIngredientByName("Sauerkraut")),
            new MealComposition(24L, 500.0, Unit.GRAM, getMealById(4L), getIngredientByName("White cabbage")),
            new MealComposition(25L, 300.0, Unit.GRAM, getMealById(4L), getIngredientByName("Sausage")),
            new MealComposition(26L, 200.0, Unit.GRAM, getMealById(4L), getIngredientByName("Bacon")),
            new MealComposition(27L, 50.0, Unit.GRAM, getMealById(4L), getIngredientByName("Dried mushrooms")),
            new MealComposition(28L, 100.0, Unit.GRAM, getMealById(4L), getIngredientByName("Dried plums")),
            new MealComposition(29L, 2.0, Unit.PIECE, getMealById(4L), getIngredientByName("Onion")),
            new MealComposition(30L, 2.0, Unit.PIECE, getMealById(4L), getIngredientByName("Bay leaves")),
            new MealComposition(31L, 5.0, Unit.PIECE, getMealById(4L), getIngredientByName("Allspice")),
            new MealComposition(32L, 1.0, Unit.TEASPOON, getMealById(4L), getIngredientByName("Pepper")),
            new MealComposition(33L, 1.0, Unit.TEASPOON, getMealById(4L), getIngredientByName("Salt")),
            new MealComposition(34L, 200.0, Unit.MILLILITER, getMealById(4L), getIngredientByName("Red wine")),

            //meal 5
            new MealComposition(35L, 400.0, Unit.GRAM, getMealById(5L), getIngredientByName("Lasagna sheets")),
            new MealComposition(36L, 500.0, Unit.GRAM, getMealById(5L), getIngredientByName("Ground meat")),
            new MealComposition(37L, 400.0, Unit.GRAM, getMealById(5L), getIngredientByName("Canned tomatoes")),
            new MealComposition(38L, 300.0, Unit.GRAM, getMealById(5L), getIngredientByName("Mozzarella")),
            new MealComposition(39L, 100.0, Unit.GRAM, getMealById(5L), getIngredientByName("Parmesan")),
            new MealComposition(40L, 3.0, Unit.CLOVE, getMealById(5L), getIngredientByName("Garlic")),
            new MealComposition(41L, 1.0, Unit.PIECE, getMealById(5L), getIngredientByName("Onion")),
            new MealComposition(42L, 2.0, Unit.TABLESPOON, getMealById(5L), getIngredientByName("Olive oil")),
            new MealComposition(43L, 1.0, Unit.TEASPOON, getMealById(5L), getIngredientByName("Salt")),
            new MealComposition(44L, 1.0, Unit.TEASPOON, getMealById(5L), getIngredientByName("Pepper")),
            new MealComposition(45L, 500.0, Unit.MILLILITER, getMealById(5L), getIngredientByName("Milk")),
            new MealComposition(46L, 50.0, Unit.GRAM, getMealById(5L), getIngredientByName("Butter")),
            new MealComposition(47L, 50.0, Unit.GRAM, getMealById(5L), getIngredientByName("Flour"))
    ));
}