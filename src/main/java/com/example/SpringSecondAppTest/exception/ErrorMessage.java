package com.example.SpringSecondAppTest.exception;

public class ErrorMessage {
    private ErrorMessage(){}

    public static final String INGREDIENT_NOT_FOUND = "ingredient with %d id not exist";
    public static final String INGREDIENT_WITH_NAME_FOUND = "ingredient with %s familyName not exist";
    public static final String INGREDIENT_WITH_NAME_NOT_EXIST_FOR_USER = "ingredient with %s familyName exist";
    public static final String INGREDIENT_ALREADY_EXIST = "ingredient already existed";
    public static final String MEAL_NOT_FOUND = "meal with %d id not exist";
    public static final String MEAL_NOT_FOUND_FOR_USER = "meal with %d id not contain for logged user";
    public static final String MEAL_WITHOUT_INGREDIENT = "meal without ingredients could not exist";
    public static final String MEAL_ALREADY_EXIST = "meal already existed";
    public static final String MEAL_NAME_ALREADY_EXIST = "familyName of meal already existed";
    public static final String CUISINE_NOT_FOUND = "cuisine not exist";
    public static final String FAMILY_NOT_FOUND = "The specified family does not exist.";
    public static final String USER_ALREADY_IN_FAMILY = "The user is already a member of this family.";
    public static final String USER_NOT_IN_FAMILY = "The user must belong to at least one family.";
    public static final String MINIMUM_FAMILY_MEMBERSHIP = "The user must belong to at least one family to perform this action.";
    public static final String LOGIN_EXIST = "login already exists";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_HAS_NO_FAMILIES = "No family found for the user. User must have at least one associated family.";
}
