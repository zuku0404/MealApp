package com.example.SpringSecondAppTest.sample;

import com.example.SpringSecondAppTest.cuisine.Cuisine;
import com.example.SpringSecondAppTest.cuisine.CuisineType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CuisineSample {
    public static final List<Cuisine> CUISINES = new ArrayList<>(Arrays.asList(
            new Cuisine(1L, CuisineType.ITALIAN),
            new Cuisine(2L, CuisineType.POLISH)
    ));
}
