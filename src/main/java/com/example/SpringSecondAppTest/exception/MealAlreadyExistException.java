package com.example.SpringSecondAppTest.exception;

public class MealAlreadyExistException extends RuntimeException{

    public MealAlreadyExistException(){}
    public MealAlreadyExistException(String message){
        super(message);
    }
}