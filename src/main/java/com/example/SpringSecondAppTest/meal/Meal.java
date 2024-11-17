package com.example.SpringSecondAppTest.meal;

import com.example.SpringSecondAppTest.cuisine.Cuisine;
import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.ingredient_meal.IngredientMeal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private List<IngredientMeal> ingredientMeals = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;

    public Meal(String name, String description, Cuisine cuisine) {
        this.name = name;
        this.description = description;
        this.cuisine = cuisine;
    }

    public void addIngredientMeal(IngredientMeal ingredientMeal) {
        ingredientMeals.add(ingredientMeal);
        ingredientMeal.setMeal(this);
    }
}
