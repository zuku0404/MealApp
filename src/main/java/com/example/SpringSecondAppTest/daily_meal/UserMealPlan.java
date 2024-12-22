//package com.example.SpringSecondAppTest.daily_meal;
//
//
//import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndUserMealsView;
//import com.example.SpringSecondAppTest.user.User;
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
//public class UserMealPlan {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private LocalDate date;
//
//    @ManyToOne
//    @JoinColumn(familyName = "user_id")
//    private Set<User> users = new HashSet<>();
//
//    @ManyToOne
//    @JoinColumn(familyName = "meal_id")
//    private Set<GlobalAndUserMealsView> meals = new HashSet<>();
//}
