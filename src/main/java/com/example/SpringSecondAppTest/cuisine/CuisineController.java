package com.example.SpringSecondAppTest.cuisine;


import com.example.SpringSecondAppTest.cuisine.dto.CuisineDto;
import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuisines")
@RequiredArgsConstructor
public class CuisineController {

    private final CuisineService cuisineService;

    @GetMapping
    public List<CuisineDto> findAll() {
        return cuisineService.findAll();
    }

    @GetMapping("/{id}/meals")
    public List<BasicMealDto> getMealsByCuisine(
            @PathVariable("id") Long id, @RequestParam(required = false, defaultValue = "0") int page) {
        return cuisineService.findMealsByCuisine(id, page);
    }
}
