package com.example.SpringSecondAppTest.exception.custom;

public class MealAlreadyExistException extends RuntimeException{

    public MealAlreadyExistException(){}
    public MealAlreadyExistException(String message){
        super(message);
    }
}