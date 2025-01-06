//package com.example.SpringSecondAppTest.daily_meal;
//
//
//import com.example.SpringSecondAppTest.family.Family;
//import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsView;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//public class DailyMeal {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private LocalDate date;
//
//    @ManyToOne
//    @JoinColumn(name = "family_id")
//    private Set<Family> families = new HashSet<>();
//
//    @ManyToOne
//    @JoinColumn(name = "mealId")
//    private Set<GlobalAndFamilyMealsView> meals = new HashSet<>();
//}
