package com.example.SpringSecondAppTest.family_meal;

import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealWithoutIdDto;
import com.example.SpringSecondAppTest.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/families/meals")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class FamilyMealController {
    private final FamilyMealService familyMealService;

    @GetMapping
    public List<BasicMealDto> getMealsForFamily(
            @AuthenticationPrincipal User user) {
        return familyMealService.getMealsForFamily(user);
    }

    @GetMapping("/accessible")
    public List<BasicMealDto> getAllAccessibleMeals(
            @AuthenticationPrincipal User user) {
        return familyMealService.getAllAccessibleMeals(user);
    }

    @GetMapping("/{id}")
    public DetailedMealDto getMeal(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long id) {
        return familyMealService.getMeal(user, id);
    }

    @PostMapping
    public DetailedMealDto addFamilyMeal(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody DetailedMealWithoutIdDto meal) {
        return familyMealService.addFamilyMeal(user, meal);
    }

    @PutMapping("/{id}")
    public DetailedMealDto updateMeal(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long id,
            @Valid @RequestBody DetailedMealWithoutIdDto meal) {
        return familyMealService.updateMeal(user, id, meal);
    }

    @DeleteMapping("/{id}")
    public void deleteMeal(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Long mealId) {
        familyMealService.deleteMeal(user, mealId);
    }
}
