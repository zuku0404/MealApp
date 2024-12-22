package com.example.SpringSecondAppTest.family_composition;//package com.zachaczuk.foodieApp.user.user_ingredient_meal;

import com.example.SpringSecondAppTest.meal_composition.Unit;
import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.family_ingredient.FamilyIngredient;
import com.example.SpringSecondAppTest.family_meal.FamilyMeal;
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
@Table(name = "family_meal_composition")
public class FamilyMealComposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double count;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_meal_id")
    private FamilyMeal familyMeal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_ingredient_id")
    private FamilyIngredient familyIngredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    public FamilyMealComposition(Double count, Unit unit, FamilyIngredient familyIngredient) {
        this.count = count;
        this.unit = unit;
        this.familyIngredient = familyIngredient;
    }

    public FamilyMealComposition(Double count, Unit unit, Ingredient ingredient) {
        this.count = count;
        this.unit = unit;
        this.ingredient = ingredient;
    }
}