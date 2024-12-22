package com.example.SpringSecondAppTest.exception.custom;

public class UserAlreadyInFamilyException extends RuntimeException {
    public UserAlreadyInFamilyException(String message) {
        super(message);
    }
}
