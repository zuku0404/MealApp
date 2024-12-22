package com.example.SpringSecondAppTest.exception.custom;

public class UserHasNoFamiliesException extends RuntimeException {
    public UserHasNoFamiliesException(String message) {
        super(message);
    }
}
