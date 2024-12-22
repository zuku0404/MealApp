package com.example.SpringSecondAppTest.family_ingredient;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.family_composition.FamilyMealComposition;
import com.example.SpringSecondAppTest.ingerdient.IngredientCategory;
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
@Table(name = "family_ingredient")
public class FamilyIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "ingredient_category")
    private IngredientCategory ingredientCategory;

    @OneToMany(mappedBy = "familyIngredient", cascade = CascadeType.REMOVE)
    private List<FamilyMealComposition> ingredientMeals = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    private Family family;

    public FamilyIngredient(String name, IngredientCategory ingredientCategory) {
        this.name = name;
        this.ingredientCategory = ingredientCategory;
    }

    public FamilyIngredient(String name, IngredientCategory ingredientCategory, Family family) {
        this.name = name;
        this.ingredientCategory = ingredientCategory;
        this.family = family;
    }
}