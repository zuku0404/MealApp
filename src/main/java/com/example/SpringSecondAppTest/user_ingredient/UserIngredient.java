package com.example.SpringSecondAppTest.user_ingredient;

import com.example.SpringSecondAppTest.ingerdient.IngredientCategory;
import com.example.SpringSecondAppTest.user.User;
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
@Table(name = "user_ingredient")
public class UserIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "ingredient_category")
    private IngredientCategory ingredientCategory;

    @OneToMany(mappedBy = "userIngredient", cascade = CascadeType.REMOVE)
    private List<UserIngredientMeal> ingredientMeals;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public UserIngredient(String name, IngredientCategory ingredientCategory) {
        this.name = name;
        this.ingredientCategory = ingredientCategory;
    }

    public UserIngredient(String name, IngredientCategory ingredientCategory, User user) {
        this.name = name;
        this.ingredientCategory = ingredientCategory;
        this.user = user;
    }
}