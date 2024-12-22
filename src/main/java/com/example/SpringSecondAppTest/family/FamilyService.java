package com.example.SpringSecondAppTest.family;

import com.example.SpringSecondAppTest.exception.ErrorMessage;
import com.example.SpringSecondAppTest.exception.custom.*;
import com.example.SpringSecondAppTest.family.dto.FamilyDto;
import com.example.SpringSecondAppTest.family.dto.FamilyDtoWithoutId;
import com.example.SpringSecondAppTest.family.dto.FamilyMapper;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FamilyService {
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;

    public Long getAccessToken(User user) {
        return user.getCurrentFamily().getId();
    }

    public FamilyDto getCurrentFamily(User user) {
        Long currentUserFamilyId = user.getCurrentFamily().getId();
        Family currentUserFamily = findFamilyByIdOrThrow(currentUserFamilyId);
        return FamilyMapper.mapToFamilyDto(currentUserFamily);
    }

    public List<FamilyDto> getFamiliesForUser(User user) {
        return FamilyMapper.mapToFamilyDtos(
                familyRepository.findByUserId(user.getId()));
    }

    @Transactional
    public FamilyDto createNewFamily(User user, FamilyDtoWithoutId familyDetails) {
        Family family = new Family(familyDetails.name(), Collections.singleton(user));
        family = familyRepository.save(family);
        user.setCurrentFamily(family);
        userRepository.save(user);
        return FamilyMapper.mapToFamilyDto(family);
    }

    @Transactional
    public FamilyDto editFamilyDetails(User authenticatedUser, Long id, FamilyDtoWithoutId familyDetails) {
        User user = findUserByIdOrThrow(authenticatedUser.getId());
        Family family = findFamilyByIdOrThrow(id);
        validateUserInFamily(user, family);
        if (!Objects.equals(family.getName(), familyDetails.name())) {
            family.setName(familyDetails.name());
            family = familyRepository.save(family);
        }
        return FamilyMapper.mapToFamilyDto(family);
    }

    @Transactional
    public FamilyDto switchCurrentFamily(User authenticatedUser, Long familyId) {
        User user = findUserByIdOrThrow(authenticatedUser.getId());
        Family family = findFamilyByIdOrThrow(familyId);
        validateUserInFamily(user, family);
        user.setCurrentFamily(family);
        User updatedUser = userRepository.save(user);
        return FamilyMapper.mapToFamilyDto(updatedUser.getCurrentFamily());
    }

    @Transactional
    public void addUserToExistingFamily(User authenticatedUser, Long familyId) {
        User user = findUserByIdOrThrow(authenticatedUser.getId());
        Family family = findFamilyByIdOrThrow(familyId);
        if (family.getFamilyMembers().contains(user)) {
            throw new UserAlreadyInFamilyException(ErrorMessage.USER_ALREADY_IN_FAMILY);
        }
        family.addUserToFamily(user);
        family = familyRepository.save(family);
        user.setCurrentFamily(family);
        userRepository.save(user);
    }

    @Transactional
    public void leaveFamily(User authenticatedUser, Long familyId) {
        User user = findUserByIdOrThrow(authenticatedUser.getId());
        Family family = findFamilyByIdOrThrow(familyId);
        validateUserInFamily(user, family);
        validateMinimumFamilyMembership(user);
        if (family.getFamilyMembers().size() == 1) {
            familyRepository.deleteById(familyId);
        }
        family.removeUserFromFamily(user);
        setNewCurrentFamilyIfNeeded(user, family);
    }

    private Family findFamilyByIdOrThrow(Long familyId) {
        return familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(ErrorMessage.FAMILY_NOT_FOUND));
    }

    private User findUserByIdOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
    }

    private void setNewCurrentFamilyIfNeeded(User user, Family family) {
        if (user.getCurrentFamily().equals(family)) {
            Family firstFamily = user.getFamilies().stream()
                    .findFirst()
                    .orElseThrow(() -> new UserHasNoFamiliesException(ErrorMessage.USER_HAS_NO_FAMILIES));
            user.setCurrentFamily(firstFamily);
        }
    }

    private void validateMinimumFamilyMembership(User user) {
        List<Family> userFamilies = familyRepository.findByUserId(user.getId());
        if (userFamilies.size() <= 1) {
            throw new MinimumFamilyMembershipException(ErrorMessage.MINIMUM_FAMILY_MEMBERSHIP);
        }
    }

    private void validateUserInFamily(User user, Family family) {
        if (!family.getFamilyMembers().contains(user)) {
            throw new UserNotInFamilyException(ErrorMessage.USER_NOT_IN_FAMILY);
        }
    }
}
