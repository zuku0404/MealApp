//package com.example.SpringSecondAppTest.daily_meal;
//
//
//import com.example.SpringSecondAppTest.daily_meal.dto.UserMealPlanDto;
//import com.example.SpringSecondAppTest.user.User;
//import com.example.SpringSecondAppTest.user_meal.UserMeal;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/userMealPlans")
//public class UserMealPlanController {
//    private final UserMealPlanService userMealPlanService;
//
//    @GetMapping()
//    public List<UserMeal> getUserMealPlan(
//            @AuthenticationPrincipal User user) {
//        return userMealPlanService.getPlanForUser(user);
//    }
//
//    @PostMapping
//    public List<UserMealPlanDto> createUserMealPlan(
//            @AuthenticationPrincipal User user) {
//        return userMealPlanService.createUserMealPlan(user);
//    }
//
//
//}
