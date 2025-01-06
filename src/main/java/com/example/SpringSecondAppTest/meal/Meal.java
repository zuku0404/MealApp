package com.example.SpringSecondAppTest.meal;

import com.example.SpringSecondAppTest.cuisine.Cuisine;
import com.example.SpringSecondAppTest.meal_composition.MealComposition;
import com.example.SpringSecondAppTest.views.Source;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private final Source source = Source.GLOBAL;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private Set<MealComposition> mealCompositions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;

    public Meal(String name, String description, Cuisine cuisine) {
        this.name = name;
        this.description = description;
        this.cuisine = cuisine;
    }

    public Meal(Long id, String name, String description, String imageUrl, Cuisine cuisine) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.cuisine = cuisine;
    }

    public void addIngredientMeal(MealComposition mealComposition) {
        mealCompositions.add(mealComposition);
        mealComposition.setMeal(this);
    }
}
