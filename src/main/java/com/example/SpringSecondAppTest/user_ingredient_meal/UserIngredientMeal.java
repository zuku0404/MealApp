package com.example.SpringSecondAppTest.user_ingredient_meal;//package com.zachaczuk.foodieApp.user.user_ingredient_meal;

import com.example.SpringSecondAppTest.ingredient_meal.Unit;
import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.user_ingredient.UserIngredient;
import com.example.SpringSecondAppTest.user_meal.UserMeal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_meal_ingredient")
public class UserIngredientMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double count;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_meal_id")
    private UserMeal userMeal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_ingredient_id")
    private UserIngredient userIngredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    public UserIngredientMeal(Double count, Unit unit, UserIngredient userIngredient) {
        this.count = count;
        this.unit = unit;
        this.userIngredient = userIngredient;
    }

    public UserIngredientMeal(Double count, Unit unit, Ingredient ingredient) {
        this.count = count;
        this.unit = unit;
        this.ingredient = ingredient;
    }
}