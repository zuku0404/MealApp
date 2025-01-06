package com.example.SpringSecondAppTest.views.global_user_meals_view;

import com.example.SpringSecondAppTest.views.Source;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalAndFamilyMealsViewId implements Serializable {

    @Column(name = "meal_id")
    private Long mealId;

    @Enumerated(EnumType.STRING)
    private Source source;
}
