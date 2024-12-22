package com.example.SpringSecondAppTest.exception.custom;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(String message) {
        super(message);
    }
}
