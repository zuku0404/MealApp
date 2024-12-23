package com.example.SpringSecondAppTest.sample.meal;

import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.ingerdient.IngredientCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientSample {
    private IngredientSample(){}

    public static final List<Ingredient> INGREDIENTS = new ArrayList<>(Arrays.asList(
            new Ingredient(1L, "Tomato", IngredientCategory.VEGETARIAN),
            new Ingredient(2L, "Mozzarella", IngredientCategory.VEGETARIAN),
            new Ingredient(3L, "Flour", IngredientCategory.VEGAN),
            new Ingredient(4L, "Yeast", IngredientCategory.VEGAN),
            new Ingredient(5L, "Olive oil", IngredientCategory.VEGAN),
            new Ingredient(6L, "Salt", IngredientCategory.VEGAN),
            new Ingredient(7L, "Water", IngredientCategory.VEGAN),
            new Ingredient(8L, "Spaghetti", IngredientCategory.VEGAN),
            new Ingredient(9L, "Bacon (pancetta)", IngredientCategory.MEAT),
            new Ingredient(10L, "Pecorino/Parmesan cheese", IngredientCategory.VEGETARIAN),
            new Ingredient(11L, "Eggs", IngredientCategory.VEGETARIAN),
            new Ingredient(12L, "Garlic", IngredientCategory.VEGAN),
            new Ingredient(13L, "Black pepper", IngredientCategory.VEGAN),
            new Ingredient(14L, "Potatoes", IngredientCategory.VEGAN),
            new Ingredient(15L, "Cottage cheese", IngredientCategory.VEGETARIAN),
            new Ingredient(16L, "Onion", IngredientCategory.VEGAN),
            new Ingredient(17L, "Butter", IngredientCategory.VEGETARIAN),
            new Ingredient(18L, "Sauerkraut", IngredientCategory.VEGAN),
            new Ingredient(19L, "White cabbage", IngredientCategory.VEGAN),
            new Ingredient(20L, "Sausage", IngredientCategory.MEAT),
            new Ingredient(21L, "Bacon", IngredientCategory.MEAT),
            new Ingredient(22L, "Dried mushrooms", IngredientCategory.VEGAN),
            new Ingredient(23L, "Dried plums", IngredientCategory.VEGAN),
            new Ingredient(24L, "Bay leaves", IngredientCategory.VEGAN),
            new Ingredient(25L, "Allspice", IngredientCategory.VEGAN),
            new Ingredient(26L, "Red wine", IngredientCategory.VEGAN),
            new Ingredient(27L, "Lasagna sheets", IngredientCategory.VEGAN),
            new Ingredient(28L, "Ground meat (beef/pork)", IngredientCategory.MEAT),
            new Ingredient(29L, "Canned tomatoes", IngredientCategory.VEGAN),
            new Ingredient(30L, "Milk", IngredientCategory.VEGETARIAN),
            new Ingredient(31L, "Butter (for Béchamel)", IngredientCategory.VEGETARIAN),
            new Ingredient(32L, "Flour (for Béchamel)", IngredientCategory.VEGAN)
    ));
}
