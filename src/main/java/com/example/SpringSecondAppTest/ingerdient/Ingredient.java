package com.example.SpringSecondAppTest.ingerdient;

import com.example.SpringSecondAppTest.meal_composition.MealComposition;
import com.example.SpringSecondAppTest.family_composition.FamilyMealComposition;
import com.example.SpringSecondAppTest.views.Source;
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
    private final Source source = Source.GLOBAL;

    @Enumerated(EnumType.STRING)
    @Column(name = "ingredient_category")
    private IngredientCategory ingredientCategory;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.REMOVE)
    private List<MealComposition> mealCompositions;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.REMOVE)
    private List<FamilyMealComposition> familyMealCompositions;

    public Ingredient(String name, IngredientCategory ingredientCategory) {
        this.name = name;
        this.ingredientCategory = ingredientCategory;
    }

    public Ingredient(Long id, String name, IngredientCategory ingredientCategory) {
        this.id = id;
        this.name = name;
        this.ingredientCategory = ingredientCategory;
    }
}
