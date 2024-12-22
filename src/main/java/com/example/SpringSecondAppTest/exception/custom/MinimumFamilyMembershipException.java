package com.example.SpringSecondAppTest.exception.custom;

public class MinimumFamilyMembershipException extends RuntimeException {
    public MinimumFamilyMembershipException(String message) {
        super(message);
    }
}
