package com.example.SpringSecondAppTest.ingerdient;

import com.example.SpringSecondAppTest.ingredient_meal.IngredientMeal;
import com.example.SpringSecondAppTest.user_ingredient_meal.UserIngredientMeal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "ingredient_category")
    private IngredientCategory ingredientCategory;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.REMOVE)
    private List<IngredientMeal> ingredientMeals;

    @OneToMany(mappedBy = "ingredient")
    private List<UserIngredientMeal> userIngredientMeals;

    public Ingredient(String name, IngredientCategory ingredientCategory) {
        this.name = name;
        this.ingredientCategory = ingredientCategory;
    }
}
