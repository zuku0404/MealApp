package com.example.SpringSecondAppTest.ingredient_meal;

import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.meal.Meal;
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
@Table(name = "meal_ingredient")
public class IngredientMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double count;
    @Enumerated(EnumType.STRING)
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    public IngredientMeal(Long id, Double count, Unit unit, Ingredient ingredient) {
        this.id = id;
        this.count = count;
        this.unit = unit;
        this.ingredient = ingredient;
    }

    public IngredientMeal(Double count, Unit unit, Ingredient ingredient) {
        this.count = count;
        this.unit = unit;
        this.ingredient = ingredient;
    }
}
