package com.example.SpringSecondAppTest.cuisine;

public enum CuisineType {
    POLISH (1),
    ITALIAN (2);


    private final int number;

    CuisineType(int number){
        this.number = number;
    }
}
