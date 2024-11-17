package com.example.SpringSecondAppTest.exception;

public class ErrorMessage {
    private ErrorMessage(){}

    public static final String INGREDIENT_NOT_FOUND = "ingredient with %d id not exist";
    public static final String INGREDIENT_WITH_NAME_FOUND = "ingredient with %s id not exist";
    public static final String INGREDIENT_ALREADY_EXIST = "ingredient already existed";
    public static final String MEAL_NOT_FOUND = "meal with %d id in %s table not exist";
    public static final String MEAL_NOT_FOUND_FOR_USER = "meal with %d id not contain for logged user";
    public static final String MEAL_WITHOUT_INGREDIENT = "meal without ingredients could not exist";
    public static final String MEAL_ALREADY_EXIST = "meal already existed";
    public static final String MEAL_NAME_ALREADY_EXIST = "name of meal already existed";
    public static final String CUISINE_NOT_FOUND = "cuisine not exist";
}
