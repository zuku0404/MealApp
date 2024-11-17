package com.example.SpringSecondAppTest.cuisine;

import com.example.SpringSecondAppTest.cuisine.dto.CuisineDto;
import com.example.SpringSecondAppTest.cuisine.dto.CuisineDtoMapper;
import com.example.SpringSecondAppTest.meal.Meal;
import com.example.SpringSecondAppTest.meal.dto.BasicMealDto;
import com.example.SpringSecondAppTest.meal.dto.MealDtoMapper;
import com.example.SpringSecondAppTest.meal.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuisineService {
    private final CuisineRepository cuisineRepository;
    private final MealRepository mealRepository;
    private static int PAGE_SIZE = 2;

    public List<CuisineDto> findAll() {
        List<Cuisine> cuisines = cuisineRepository.findAll();
        return CuisineDtoMapper.mapToCuisineDtos(cuisines);
    }

    public List<BasicMealDto> findMealsByCuisine(Long id, int page) {
        int currentPage = page >= 1 ? page - 1 : 0;
        List<Meal> meals = mealRepository.findMealsByCuisineId(id, PageRequest.of(currentPage, PAGE_SIZE));
        return MealDtoMapper.mapToBasicMealDtos(meals);
    }
}
