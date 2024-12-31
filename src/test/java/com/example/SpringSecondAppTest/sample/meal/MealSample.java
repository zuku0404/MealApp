package com.example.SpringSecondAppTest.sample.meal;

import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.meal.Meal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.SpringSecondAppTest.sample.SampleHelper.getCuisineByCuisineType;

public class MealSample {
    private MealSample(){}

    public static final List<Meal> MEALS;

    static {
        MEALS = Collections.unmodifiableList(create());
    }

    public static List<Meal> create() {
        return new ArrayList<>(Arrays.asList(
                new Meal(1L, "Margherita Pizza", "Classic Italian pizza with tomato and cheese",
                        "http://localhost:8080/images/margarita.jpg", getCuisineByCuisineType(CuisineType.ITALIAN)),
                new Meal(2L, "Spaghetti Carbonara", "Pasta with eggs, cheese, and pancetta",
                        "http://localhost:8080/images/spaghetti.jpg", getCuisineByCuisineType(CuisineType.ITALIAN)),
                new Meal(3L, "Pierogi", "Polish dumplings with various fillings",
                        "http://localhost:8080/images/pierogi.jpg", getCuisineByCuisineType(CuisineType.POLISH)),
                new Meal(4L, "Bigos", "Traditional Polish hunter’s stew",
                        "http://localhost:8080/images/bigos.jpg", getCuisineByCuisineType(CuisineType.POLISH)),
                new Meal(5L, "Lasagna", "Layers of pasta, cheese, and meat sauce",
                        "http://localhost:8080/images/lasagna.jpg", getCuisineByCuisineType(CuisineType.ITALIAN))
        ));
    }
}
