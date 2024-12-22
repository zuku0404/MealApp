package com.example.SpringSecondAppTest.exception.custom;

public class FamilyNotFoundException extends RuntimeException {
    public FamilyNotFoundException(String message) {
        super(message);
    }
}
