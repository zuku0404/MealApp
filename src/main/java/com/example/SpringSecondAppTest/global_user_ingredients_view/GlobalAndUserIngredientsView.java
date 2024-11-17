package com.example.SpringSecondAppTest.global_user_ingredients_view;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "ingredient_view")
public class GlobalAndUserIngredientsView {
    @Id
    @Column (name = "row_id")
    private Long rowId;

    private Long id;
    private String name;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Source source;
}
