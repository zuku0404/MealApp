package com.example.SpringSecondAppTest.family;

import com.example.SpringSecondAppTest.family.dto.FamilyDto;
import com.example.SpringSecondAppTest.family.dto.FamilyDtoWithoutId;
import com.example.SpringSecondAppTest.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/families")
@PreAuthorize("hasRole('USER')")
public class FamilyController {
    private final FamilyService familyService;

    @GetMapping("/access-token")
    public Long getAccessToken(@AuthenticationPrincipal User user) {
        return familyService.getAccessToken(user);
    }

    @GetMapping("/current")
    public FamilyDto getCurrentFamily(@AuthenticationPrincipal User user) {
        return familyService.getCurrentFamily(user);
    }

    @GetMapping("/user")
    public List<FamilyDto> getFamiliesForUser(@AuthenticationPrincipal User user) {
        return familyService.getFamiliesForUser(user);
    }

    @PostMapping
    public FamilyDto createFamily(@AuthenticationPrincipal User user,
                                  @Valid @RequestBody FamilyDtoWithoutId familyDtoWithoutId) {
        return familyService.createNewFamily(user, familyDtoWithoutId);
    }

    @PostMapping("/{id}/switch-current")
    public FamilyDto switchCurrentFamily(@AuthenticationPrincipal User user,
                                         @PathVariable("id") Long familyId) {
        return familyService.switchCurrentFamily(user, familyId);
    }

    @PutMapping("/{id}")
    public FamilyDto editFamilyDetails(@AuthenticationPrincipal User user,
                                       @PathVariable("id") Long id,
                                       @RequestBody FamilyDtoWithoutId familyDetails) {
        return familyService.editFamilyDetails(user, id, familyDetails);
    }

    @PatchMapping("/join/{id}")
    public ResponseEntity<String> addUserToExistingFamily(@AuthenticationPrincipal User user,
                                                          @PathVariable("id") Long familyId) {
        familyService.addUserToExistingFamily(user, familyId);
        return ResponseEntity.ok("Successfully joined the family.");
    }

    @PatchMapping("/leave/{id}")
    public ResponseEntity<String> leaveFamily(@AuthenticationPrincipal User user,
                                              @PathVariable("id") Long familyId) {
        familyService.leaveFamily(user, familyId);
        return ResponseEntity.ok("Successfully left the family.");
    }
}
