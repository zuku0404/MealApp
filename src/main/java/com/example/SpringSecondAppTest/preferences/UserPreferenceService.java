//package com.example.SpringSecondAppTest.preferences;
//
//import com.example.SpringSecondAppTest.user.User;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserPreferenceService {
//    private final UserPreferenceRepository userPreferenceRepository;
//    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
//
//    public void getUserPreference(User user) {
//        Optional<UserPreference> preferenceByUserId = userPreferenceRepository.findPreferenceByUserId(user.getId());
//        if(preferenceByUserId.isEmpty()) {
//            logger.info("preference is not set yet");
//        } else {
//            UserPreference userPreference = preferenceByUserId.get();
//            userPreference.getCuisines().forEach(cuisine -> logger.info(cuisine.getCuisineType().familyName()));
//        }
//    }
//
//    public void updateUserPreference(User user, UserPreference userPreference) {
//        userPreferenceRepository.findById(userPreference.getId());
//        userPreferenceRepository.save(userPreference);
//    }
//}
