package com.example.SpringSecondAppTest.meal_frequency;

import com.example.SpringSecondAppTest.exception.ErrorMessage;
import com.example.SpringSecondAppTest.exception.custom.MealDoesNotBelongToUserFamilyException;
import com.example.SpringSecondAppTest.exception.custom.MealNotFoundException;
import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.meal_frequency.dto.MealFrequencyDto;
import com.example.SpringSecondAppTest.meal_frequency.dto.MealFrequencyMapper;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.views.Source;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsView;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsViewId;
import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealFrequencyService {
    private static final Frequency DEFAULT_FREQUENCY = Frequency.ONCE_MONTH;
    private final MealFrequencyRepository mealFrequencyRepository;
    private final GlobalAndFamilyMealsViewRepository globalAndFamilyMealsViewRepository;

    public MealFrequencyDto getFrequencyDetails(Long mealId, Source source, User user) {
        Family userCurrentFamily = user.getCurrentFamily();
        GlobalAndFamilyMealsView meal = globalAndFamilyMealsViewRepository.findById(
                        new GlobalAndFamilyMealsViewId(mealId, source))
                .orElseThrow(() -> new MealNotFoundException(String.format(ErrorMessage.MEAL_NOT_FOUND, mealId)));
        validateRequest(source, userCurrentFamily, meal);
        return MealFrequencyMapper.mapToMealFrequencyDto(mealFrequencyRepository.findByMealAndFamily(meal, userCurrentFamily)
                .orElse(new MealFrequency(user.getCurrentFamily(), meal, DEFAULT_FREQUENCY)));
    }

    public void setFrequency(Long mealId, Source source, Frequency frequency, User user) {
        Family userCurrentFamily = user.getCurrentFamily();
        GlobalAndFamilyMealsView meal = globalAndFamilyMealsViewRepository.findById(
                        new GlobalAndFamilyMealsViewId(mealId, source))
                .orElseThrow(() -> new MealNotFoundException(String.format(ErrorMessage.MEAL_NOT_FOUND, mealId)));
        validateRequest(source, userCurrentFamily, meal);
        Optional<MealFrequency> mealFrequency = mealFrequencyRepository.findByMealAndFamily(meal, userCurrentFamily);
        if (mealFrequency.isPresent()) {
            if (frequency == DEFAULT_FREQUENCY) {
                mealFrequencyRepository.deleteById(mealFrequency.get().getId());
            } else {
                mealFrequency.get().setFrequency(frequency);
                mealFrequencyRepository.save(mealFrequency.get());
            }
        } else if (frequency != DEFAULT_FREQUENCY) {
            mealFrequencyRepository.save(new MealFrequency(user.getCurrentFamily(), meal, frequency));
        }
    }

    private void validateRequest(Source source, Family userCurrentFamily, GlobalAndFamilyMealsView meal) {
        if (source == Source.CUSTOM && !Objects.equals(userCurrentFamily.getId(), meal.getFamilyId())) {
            throw new MealDoesNotBelongToUserFamilyException(String.format("The meal with ID %d and source %s does not belong to the user's current family (family ID: %d).",
                    meal.getId().getMealId(), source, userCurrentFamily.getId()));
        }
    }
}
