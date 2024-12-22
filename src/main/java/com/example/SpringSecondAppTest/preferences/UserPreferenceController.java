//package com.example.SpringSecondAppTest.preferences;
//
//import com.example.SpringSecondAppTest.preferences.dto.UserPreferencesDto;
//import com.example.SpringSecondAppTest.user.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("api/preferences")
//public class UserPreferenceController {
//    private final UserPreferenceService userPreferenceService;
//
//    @GetMapping
//    @PreAuthorize("hasRole('USER')")
//    public void getUserPreferences(@AuthenticationPrincipal User user) {
//        userPreferenceService.getUserPreference(user);
//    }
//
//    @PutMapping
//    @PreAuthorize("hasRole('USER')")
//    public void updateUserPreference(@AuthenticationPrincipal User user, UserPreference userPreference){
//        userPreferenceService.updateUserPreference(user, userPreference);
//    }
//}
