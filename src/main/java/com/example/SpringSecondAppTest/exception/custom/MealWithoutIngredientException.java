package com.example.SpringSecondAppTest.exception.custom;

public class MealWithoutIngredientException extends RuntimeException {
    public MealWithoutIngredientException(String message) {
        super(message);
    }
}
