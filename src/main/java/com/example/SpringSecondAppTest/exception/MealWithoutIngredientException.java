package com.example.SpringSecondAppTest.exception;

public class MealWithoutIngredientException extends RuntimeException {
    public MealWithoutIngredientException(String message) {
        super(message);
    }
}
