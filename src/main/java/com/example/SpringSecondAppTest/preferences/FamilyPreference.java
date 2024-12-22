package com.example.SpringSecondAppTest.preferences;

import com.example.SpringSecondAppTest.cuisine.Cuisine;
import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.ingerdient.Ingredient;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.family_ingredient.FamilyIngredient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "family_preference")
public class FamilyPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "preference")
    private Family family;

    @ManyToMany
    @JoinTable(name = "family_preference_cuisine", joinColumns = @JoinColumn(name = "family_preference_id"),
            inverseJoinColumns = @JoinColumn(name = "cuisine_id"))
    private Set<Cuisine> cuisines = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "family_preference_without_ingredient", joinColumns = @JoinColumn(name = "preference_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "family_preference_without_family_ingredient", joinColumns = @JoinColumn(name = "preference_id"),
            inverseJoinColumns = @JoinColumn(name = "family_ingredient_id"))
    private Set<FamilyIngredient> familyIngredients = new HashSet<>();

    public FamilyPreference(Long id){
        this.id = id;

    }
}
