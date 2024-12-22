package com.example.SpringSecondAppTest.sample;

import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.meal.Meal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealSample {
    public static final List<Meal> MEALS = new ArrayList<>(Arrays.asList(
            new Meal(1L,"Margherita Pizza","Classic Italian pizza with tomato and cheese", "http://localhost:8080/images/margarita.jpg",
                    CuisineSample.CUISINES.stream().filter(cuisine -> cuisine.getCuisineType() == CuisineType.ITALIAN).findFirst().orElseThrow()),
            new Meal(2L,"Spaghetti Carbonara","Pasta with eggs, cheese, and pancetta", "http://localhost:8080/images/spaghetti.jpg",
                    CuisineSample.CUISINES.stream().filter(cuisine -> cuisine.getCuisineType() == CuisineType.ITALIAN).findFirst().orElseThrow()),
            new Meal(3L,"Pierogi","Polish dumplings with various fillings", "http://localhost:8080/images/pierogi.jpg",
                    CuisineSample.CUISINES.stream().filter(cuisine -> cuisine.getCuisineType() == CuisineType.POLISH).findFirst().orElseThrow()),
            new Meal(4L,"Bigos","Traditional Polish hunter’s stew", "http://localhost:8080/images/bigos.jpg",
                     CuisineSample.CUISINES.stream().filter(cuisine -> cuisine.getCuisineType() == CuisineType.POLISH).findFirst().orElseThrow()),
            new Meal(5L,"Lasagna","Layers of pasta, cheese, and meat sauce", "http://localhost:8080/images/lasagna.jpg",
                     CuisineSample.CUISINES.stream().filter(cuisine -> cuisine.getCuisineType() == CuisineType.ITALIAN).findFirst().orElseThrow())
    ));
}
