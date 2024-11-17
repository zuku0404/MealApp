//package com.example.SpringSecondAppTest.daily_meal;
//
//
//import com.example.SpringSecondAppTest.user.User;
//import com.example.SpringSecondAppTest.user_meal.UserMeal;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping()
//public class UserMealController {
//    private final UserMealPlanService userMealPlanService;
//
//    @GetMapping()
//    public List<UserMeal> getUserMealPlan(
//            @AuthenticationPrincipal User user) {
//        return userMealPlanService.getPlanForUser(user);
//    }
//
//
//}
