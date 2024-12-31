//package com.example.SpringSecondAppTest.meal_frequent;
//
//import com.example.SpringSecondAppTest.family.Family;
//import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsView;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Entity
//public class MealFrequent {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "family_id")
//    private Set<Family> families = new HashSet<>();
//
//    @ManyToOne
//    @JoinColumn(name = "meal_id")
//    private Set<GlobalAndFamilyMealsView> meals = new HashSet<>();
//
//    @Enumerated(EnumType.STRING)
//    private Frequence frequence;
//}
