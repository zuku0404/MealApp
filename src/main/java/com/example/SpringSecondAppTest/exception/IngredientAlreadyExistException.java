package com.example.SpringSecondAppTest.exception;


public class IngredientAlreadyExistException extends RuntimeException{

    public IngredientAlreadyExistException(){}
    public IngredientAlreadyExistException(String message){
        super(message);
    }
}
