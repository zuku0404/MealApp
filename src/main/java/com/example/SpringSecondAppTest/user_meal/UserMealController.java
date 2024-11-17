package com.example.SpringSecondAppTest.user_meal;

import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealWithoutIdDto;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user_meal.dto.DetailedUserMealDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/meals")
@RequiredArgsConstructor
public class UserMealController {

    private final UserMealService userMealService;

    @GetMapping("/detailed")
    @PreAuthorize("hasRole('USER')")
    public List<DetailedUserMealDto> getAllDetailed(@AuthenticationPrincipal User user) {
        return userMealService.findAllDetailed(user);
    }

    @GetMapping("/basic")
    @PreAuthorize("hasRole('USER')")
    public List<BasicMealDto> getAllBasic(@AuthenticationPrincipal User user) {
        return userMealService.findAllBasic(user);
    }

    @GetMapping("/preferences")
    @PreAuthorize("hasRole('USER')")
    public List<BasicMealDto> findAllForUserPreferences(@AuthenticationPrincipal User user) {
        return null;
//        return userMealService.getMealsForUserPreferences();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public DetailedUserMealDto getMeal(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long id) {
        return userMealService.findById(user, id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public DetailedUserMealDto createMealForUser(
            @AuthenticationPrincipal User user,
            @RequestBody DetailedMealWithoutIdDto meal) {
        return userMealService.create(user, meal);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public DetailedUserMealDto createMealForUser(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long id,
            @RequestBody DetailedMealWithoutIdDto meal) {
        return userMealService.update(user, id, meal);
    }

    @DeleteMapping("/{meal_id}")
    @PreAuthorize("hasRole('USER')")
    public void deleteIngredient(
            @AuthenticationPrincipal User user,
            @PathVariable("meal_id") Long mealId) {
        userMealService.delete(user, mealId);
    }
}
