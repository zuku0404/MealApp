package com.example.SpringSecondAppTest.meal_frequency;

import com.example.SpringSecondAppTest.meal_frequency.dto.MealFrequencyDto;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.views.Source;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
@RequestMapping("/api/meals_frequency")
public class MealFrequencyController {
    private final MealFrequencyService mealFrequencyService;

    @GetMapping("/{mealId}/{source}")
    public MealFrequencyDto getMealDetails(@PathVariable Long mealId,
                                           @PathVariable Source source,
                                           @AuthenticationPrincipal User user) {
        return mealFrequencyService.getFrequencyDetails(mealId, source, user);
    }

    @PutMapping("/{mealId}/{source}")
    public void setFrequency(@AuthenticationPrincipal User user,
                             @PathVariable Long mealId,
                             @PathVariable Source source,
                             @RequestBody Frequency frequency) {
        mealFrequencyService.setFrequency(mealId, source, frequency, user);
    }
}
