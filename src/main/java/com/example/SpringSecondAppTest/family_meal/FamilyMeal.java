package com.example.SpringSecondAppTest.family_meal;//package com.zachaczuk.foodieApp.user.user_meal;

import com.example.SpringSecondAppTest.cuisine.Cuisine;
import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.family_composition.FamilyMealComposition;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "family_meal")
public class FamilyMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "familyMeal", fetch = FetchType.LAZY)
    private Set<FamilyMealComposition> familyMealCompositions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    private Family family;

    @ManyToOne
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;

    public FamilyMeal(Long id, String name, String description, String imageUrl, Cuisine cuisine, Family family) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.cuisine = cuisine;
        this.family = family;
    }

    public FamilyMeal(String name, String description, Cuisine cuisine, Family family) {
        this.name = name;
        this.description = description;
        this.cuisine = cuisine;
        this.family = family;
    }

    public void addIngredientMeal(FamilyMealComposition ingredientMeal) {
        familyMealCompositions.add(ingredientMeal);
        ingredientMeal.setFamilyMeal(this);
    }
}
