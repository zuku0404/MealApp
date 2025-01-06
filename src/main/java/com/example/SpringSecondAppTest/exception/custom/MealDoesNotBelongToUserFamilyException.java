package com.example.SpringSecondAppTest.exception.custom;

public class MealDoesNotBelongToUserFamilyException extends RuntimeException {
    public MealDoesNotBelongToUserFamilyException(String message) {
        super(message);
    }
}
