package com.example.SpringSecondAppTest.family;

import com.example.SpringSecondAppTest.exception.ErrorMessage;
import com.example.SpringSecondAppTest.exception.custom.*;
import com.example.SpringSecondAppTest.family.dto.FamilyDto;
import com.example.SpringSecondAppTest.family.dto.FamilyDtoWithoutId;
import com.example.SpringSecondAppTest.family.dto.FamilyMapper;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user.UserRepository;
import com.example.SpringSecondAppTest.user_family.UserFamily;
import com.example.SpringSecondAppTest.user_family.UserFamilyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FamilyService {
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final UserFamilyRepository userFamilyRepository;

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
                userFamilyRepository.findFamiliesByUserId(user.getId()));
    }

    @Transactional
    public FamilyDto createNewFamily(User user, FamilyDtoWithoutId familyDetails) {
        Family family = familyRepository.save(new Family(familyDetails.name()));
        user.setCurrentFamily(family);
        userRepository.save(user);
        userFamilyRepository.save(new UserFamily(user, family));
        return FamilyMapper.mapToFamilyDto(family);
    }

    @Transactional
    public FamilyDto editFamilyDetails(User user, Long familyId, FamilyDtoWithoutId familyDetails) {
        Family family = getValidatedFamilyForUser(user, familyId);
        if (!Objects.equals(family.getName(), familyDetails.name())) {
            family.setName(familyDetails.name());
            family = familyRepository.save(family);
        }
        return FamilyMapper.mapToFamilyDto(family);
    }

    @Transactional
    public FamilyDto switchCurrentFamily(User user, Long familyId) {
        Family family = getValidatedFamilyForUser(user, familyId);
        user.setCurrentFamily(family);
        User updatedUser = userRepository.save(user);
        return FamilyMapper.mapToFamilyDto(updatedUser.getCurrentFamily());
    }

    @Transactional
    public void addUserToExistingFamily(User user, Long familyId) {
        if (userFamilyRepository.findFamilyByUserIdAndFamilyId(user.getId(), familyId).isPresent()) {
            throw new UserAlreadyInFamilyException(ErrorMessage.USER_ALREADY_IN_FAMILY);
        }
        Family family = familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(ErrorMessage.FAMILY_NOT_FOUND) );
        userFamilyRepository.save(new UserFamily(user, family));
        user.setCurrentFamily(family);
        userRepository.save(user);
    }

    @Transactional
    public void leaveFamily(User user, Long familyId) {
        Family family = getValidatedFamilyForUser(user, familyId);
        List<Family> userFamilies = userFamilyRepository.findFamiliesByUserId(user.getId());
        if (userFamilies.size() <= 1) {
            throw new MinimumFamilyMembershipException(ErrorMessage.MINIMUM_FAMILY_MEMBERSHIP);
        }
        if (family.getFamilies().size() == 1) {
            familyRepository.deleteById(familyId);
        }
        userFamilyRepository.deleteFamilyByUserIdAndFamilyId(user.getId(),familyId);
        setNewCurrentFamilyIfNeeded(user, family,userFamilies);
    }

    private Family findFamilyByIdOrThrow(Long familyId) {
        return familyRepository.findById(familyId)
                .orElseThrow(() -> new FamilyNotFoundException(ErrorMessage.FAMILY_NOT_FOUND));
    }

    private void setNewCurrentFamilyIfNeeded(User user, Family family, List<Family> userFamilies) {
        if (user.getCurrentFamily().equals(family)) {
            Family firstFindFamily = userFamilies.stream()
                    .filter(f -> !f.equals(family))
                    .findFirst()
                    .orElseThrow(() -> new UserHasNoFamiliesException(ErrorMessage.USER_HAS_NO_FAMILIES));
            user.setCurrentFamily(firstFindFamily);
        }
    }

    private Family getValidatedFamilyForUser(User user, Long familyId) {
        return userFamilyRepository.findFamilyByUserIdAndFamilyId(user.getId(), familyId)
                .orElseThrow(() -> new UserNotInFamilyException(ErrorMessage.USER_NOT_IN_FAMILY));
    }
}
