package com.example.SpringSecondAppTest.sample;

import com.example.SpringSecondAppTest.meal_composition.MealComposition;
import com.example.SpringSecondAppTest.meal_composition.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealCompositionSample {
    public static final List<MealComposition> MEAL_COMPOSITIONS = new ArrayList<>(Arrays.asList(
            new MealComposition(1L, 3.0, Unit.PIECE,
                    MealSample.MEALS.stream().filter(meal -> meal.getName().equalsIgnoreCase() "").findFirst().orElseThrow(),
                    IngredientSample.INGREDIENTS.stream().filter(ingredient -> ingredient.getName().equalsIgnoreCase("Tomato"))
                            .findFirst().orElseThrow()),
            new MealComposition(2L, 250.0, Unit.GRAM,
                    MealSample.MEALS.stream().filter(meal -> meal.getId() == 1).findFirst().orElseThrow(),
                    IngredientSample.INGREDIENTS.stream().filter(ingredient -> ingredient.getName().equalsIgnoreCase("Mozzarella"))
                            .findFirst().orElseThrow()),
            new MealComposition(3L, 500.0, Unit.GRAM,
                    MealSample.MEALS.stream().filter(meal -> meal.getId() == 1).findFirst().orElseThrow(),
                    IngredientSample.INGREDIENTS.stream().filter(ingredient -> ingredient.getName().equalsIgnoreCase("Flour"))
                            .findFirst().orElseThrow()),
            new MealComposition(4L, 1.0, Unit.PIECE,
                    MealSample.MEALS.stream().filter(meal -> meal.getId() == 1).findFirst().orElseThrow(),
                    IngredientSample.INGREDIENTS.stream().filter(ingredient -> ingredient.getName().equalsIgnoreCase("Yeast"))
                            .findFirst().orElseThrow()),
            new MealComposition(5L, 1.0, Unit.TABLESPOON,
                    MealSample.MEALS.stream().filter(meal -> meal.getId() == 1).findFirst().orElseThrow(),
                    IngredientSample.INGREDIENTS.stream().filter(ingredient -> ingredient.getName().equalsIgnoreCase("Olive oil"))
                            .findFirst().orElseThrow()),
            new MealComposition(6L, 1.0, Unit.TEASPOON,
                    MealSample.MEALS.stream().filter(meal -> meal.getId() == 1).findFirst().orElseThrow(),
                    IngredientSample.INGREDIENTS.stream().filter(ingredient -> ingredient.getName().equalsIgnoreCase("Salt"))
                            .findFirst().orElseThrow()),
            new MealComposition(7L, 200.0, Unit.MILLILITER,
                    MealSample.MEALS.stream().filter(meal -> meal.getId() == 1).findFirst().orElseThrow(),
                    IngredientSample.INGREDIENTS.stream().filter(ingredient -> ingredient.getName().equalsIgnoreCase("Water"))
                            .findFirst().orElseThrow()), ));
}
        (1,1,3.0,'PIECE'),--

Tomato
        (3,1,250.0,'GRAM'), --

Mozzarella
        (4,1,500.0,'GRAM'), --

Flour
        (5,1,1.0,'PIECE'),  --

Yeast(1packet)
(6,1,1.0,'TABLESPOON'),--

Olive oil
        (7,1,1.0,'TEASPOON'), --

Salt
        (8,1,200.0,'MILLILITER');