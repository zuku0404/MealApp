package com.example.SpringSecondAppTest.meal_frequency;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "meal_frequency")
public class MealFrequency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    private Family family;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="meal_id", referencedColumnName = "meal_id"),
            @JoinColumn(name="source", referencedColumnName = "source")
    })
    private GlobalAndFamilyMealsView meal;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    public MealFrequency(Family family, GlobalAndFamilyMealsView meal, Frequency frequency){
        this.family = family;
        this.meal = meal;
        this.frequency = frequency;
    }
}
