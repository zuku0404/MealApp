package com.example.SpringSecondAppTest.views.global_user_meals_view;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.Immutable;

@Data
@Entity
@Immutable
@Table(name = "global_family_meals_view")
public class GlobalAndFamilyMealsView {

    @EmbeddedId
    private GlobalAndFamilyMealsViewId id;

    private String name;
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "family_id")
    private Long familyId;
}
