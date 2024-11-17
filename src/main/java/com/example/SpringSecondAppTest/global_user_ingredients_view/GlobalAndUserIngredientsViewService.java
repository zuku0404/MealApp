package com.example.SpringSecondAppTest.global_user_ingredients_view;

import com.example.SpringSecondAppTest.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GlobalAndUserIngredientsViewService {

    private final GlobalAndUserIngredientsViewRepository globalAndUserIngredientsViewRepository;

    public List<GlobalAndUserIngredientsView> findAllForUser(Authentication authentication) {
        User user = (User) authentication.getDetails();
        return globalAndUserIngredientsViewRepository.findIngredientsForUserName(user.getId());
    }
}
