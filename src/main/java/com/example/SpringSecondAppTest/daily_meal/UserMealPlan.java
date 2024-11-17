package com.example.SpringSecondAppTest.daily_meal;


import com.example.SpringSecondAppTest.meal.Meal;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user_meal.UserMeal;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class UserMealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    private Set<User> users = new HashSet<>();

    @ManyToMany
    private Set<UserMeal> meals = new HashSet<>();
}
