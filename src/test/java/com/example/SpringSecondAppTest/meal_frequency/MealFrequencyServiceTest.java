//package com.example.SpringSecondAppTest.meal_frequency;
//
//import com.example.SpringSecondAppTest.sample.GlobalAndFamilyMealsViewSample;
//import com.example.SpringSecondAppTest.views.Source;
//import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsView;
//import com.example.SpringSecondAppTest.views.global_user_meals_view.GlobalAndFamilyMealsViewRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.BDDMockito.given;
//
//@ExtendWith(MockitoExtension.class)
//public class MealFrequencyServiceTest {
//
//    @Mock
//    MealFrequencyRepository mealFrequencyRepository;
//    @Mock
//    GlobalAndFamilyMealsViewRepository globalAndFamilyMealsViewRepository;
//
//    @InjectMocks
//    MealFrequencyService mealFrequencyService;
//
//    private List<GlobalAndFamilyMealsView> globalAndFamilyMealsViews;
//
//    @BeforeEach
//    public void setup() {
//        this.globalAndFamilyMealsViews = GlobalAndFamilyMealsViewSample.create();
//    }
//
//    @Nested
//    @DisplayName("Tests for getFrequencyDetails method")
//    class GetFrequencyDetails {
//        @Test
//        void shoul1() {
//            GlobalAndFamilyMealsView globalMealView = globalAndFamilyMealsViews.stream()
//                    .filter(view -> view.getId().getSource() == Source.GLOBAL)
//                    .findFirst()
//                    .orElseThrow();
//
//            given(globalAndFamilyMealsViewRepository.findById(globalMealView.getId())).willReturn(Optional.of(globalMealView));
//            mealFrequencyService.getFrequencyDetails();
//
//        }
//    }
//}
