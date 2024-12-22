package com.example.SpringSecondAppTest.exception.custom;

public class UserNotInFamilyException extends RuntimeException {
    public UserNotInFamilyException(String message) {
        super(message);
    }
}
