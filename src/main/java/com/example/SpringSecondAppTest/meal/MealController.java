package com.example.SpringSecondAppTest.meal;

import com.example.SpringSecondAppTest.cuisine.CuisineType;
import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealDto;
import com.example.SpringSecondAppTest.meal.dto.DetailedMealWithoutIdDto;
import com.example.SpringSecondAppTest.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping("/detailed")
    public List<DetailedMealDto> getAllWithDetailedData() {
        return mealService.findAllDetailed();
    }

    @GetMapping("/basic")
    public List<BasicMealDto> getAllWithBasicData(
            @RequestParam(required = false) List<CuisineType> cuisineTypes,
            @RequestParam(required = false) Integer page) {
        return mealService.findAllBasic(cuisineTypes, page);
    }

    @GetMapping("/{id}")
    public DetailedMealDto getMeal(
            @PathVariable("id") Long id) {
        return mealService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public DetailedMealDto createMealForAdmin(
            @RequestBody DetailedMealWithoutIdDto meal) {
        return mealService.saveMeal(meal);
    }

    @PutMapping("admin/{meal_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public DetailedMealDto updateMealForAdmin(
            @AuthenticationPrincipal User user,
            @PathVariable("meal_id") Long mealId,
            @RequestBody DetailedMealWithoutIdDto meal){
        return mealService.update(mealId, meal);
    }


    @DeleteMapping("/{meal_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteIngredient(@PathVariable("meal_id") Long mealId){
        mealService.delete(mealId);
    }

    @GetMapping("/pages")
    public Integer getTotalNumberOfPages(@RequestParam int pageSize){
        return mealService.getTotalNumberOfPages(pageSize);
    }
}
