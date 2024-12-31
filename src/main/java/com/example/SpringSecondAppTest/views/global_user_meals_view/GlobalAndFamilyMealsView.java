package com.example.SpringSecondAppTest.views.global_user_meals_view;


import com.example.SpringSecondAppTest.views.Source;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "global_family_meals_view")
public class GlobalAndFamilyMealsView {
    @Id
    @Column(name = "row_id")
    private Long id;

    @Column(name = "meal_id")
    private Long mealId;

    private String name;
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "family_id")
    private Long familyId;

    @Enumerated(EnumType.STRING)
    private Source source;
}
