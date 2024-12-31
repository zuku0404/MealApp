package com.example.SpringSecondAppTest.sample;

import com.example.SpringSecondAppTest.cuisine.Cuisine;
import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.ingerdient.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CuisineSample {
    private CuisineSample() {}

    public static final List<Cuisine> CUISINE;

    static {
        CUISINE = Collections.unmodifiableList(create());
    }

    public static List<Cuisine> create() {
        return new ArrayList<>(Arrays.asList(
                new Cuisine(1L, CuisineType.ITALIAN),
                new Cuisine(2L, CuisineType.POLISH),
                new Cuisine(3L, CuisineType.JAPANESE)));
    }
}
