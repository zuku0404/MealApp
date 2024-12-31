package com.example.SpringSecondAppTest.sample.family_meal;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.family_ingredient.FamilyIngredient;
import com.example.SpringSecondAppTest.ingerdient.IngredientCategory;
import com.example.SpringSecondAppTest.sample.user.FamilySample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FamilyIngredientSample {
    private FamilyIngredientSample(){}

    public static final List<FamilyIngredient> FAMILY_INGREDIENTS;

    static {
        FAMILY_INGREDIENTS = Collections.unmodifiableList(create());
    }

    public static List<FamilyIngredient> create() {
        List<Family> families = FamilySample.create();
        return new ArrayList<>(Arrays.asList(
                new FamilyIngredient(1L, "Tofu", IngredientCategory.VEGAN, families.get(1)),
                new FamilyIngredient(2L, "Chili Pepper", IngredientCategory.VEGAN, families.get(1))
        ));
    }
}