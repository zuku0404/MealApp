package com.example.SpringSecondAppTest.views.global_user_ingredients_view;

import com.example.SpringSecondAppTest.ingerdient.IngredientCategory;
import com.example.SpringSecondAppTest.views.Source;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "global_family_ingredients_view")
public class GlobalAndFamilyIngredientsView {
    @Id
    @Column (name = "row_id")
    private Long rowId;

    private Long id;
    private String name;

    @Column(name = "ingredient_category")
    private IngredientCategory ingredientCategory;

    @Column(name = "family_id")
    private Long familyId;

    @Enumerated(EnumType.STRING)
    private Source source;
}
