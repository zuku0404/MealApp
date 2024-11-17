package com.example.SpringSecondAppTest.user_meal;//package com.zachaczuk.foodieApp.user.user_meal;

import com.example.SpringSecondAppTest.cuisine.Cuisine;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user_ingredient_meal.UserIngredientMeal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_meal")
public class UserMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "userMeal", fetch = FetchType.LAZY)
    private Set<UserIngredientMeal> userIngredientMeals = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;

    public UserMeal(String name, String description, Cuisine cuisine, User user) {
        this.name = name;
        this.description = description;
        this.cuisine = cuisine;
        this.user = user;
    }

    public void addIngredientMeal(UserIngredientMeal ingredientMeal) {
        userIngredientMeals.add(ingredientMeal);
        ingredientMeal.setUserMeal(this);
    }
}
