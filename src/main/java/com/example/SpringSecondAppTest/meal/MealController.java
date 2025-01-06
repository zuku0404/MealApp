package com.example.SpringSecondAppTest.meal;

import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealWithoutIdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @GetMapping
    public List<BasicMealDto> getAllWithBasicData(
            @RequestParam(required = false) List<CuisineType> cuisineTypes) {
        return mealService.getAllWithBasicData(cuisineTypes);
    }

    @GetMapping("/{id}")
    public DetailedMealDto getMeal(
            @PathVariable("id") Long id) {
        return mealService.findMealById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public DetailedMealDto createMealForAdmin(
            @RequestBody DetailedMealWithoutIdDto meal) {
        return mealService.createMeal(meal);
    }

    @PutMapping("/{mealId}")
    @PreAuthorize("hasRole('ADMIN')")
    public DetailedMealDto updateMealForAdmin(
            @PathVariable("mealId") Long mealId,
            @RequestBody DetailedMealWithoutIdDto meal){
        return mealService.updateMeal(mealId, meal);
    }

    @DeleteMapping("/{mealId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteIngredient(@PathVariable("mealId") Long mealId){
        mealService.deleteMeal(mealId);
    }
}
