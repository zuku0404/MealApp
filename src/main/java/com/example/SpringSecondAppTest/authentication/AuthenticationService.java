package com.example.SpringSecondAppTest.authentication;


import com.example.SpringSecondAppTest.account.Account;
import com.example.SpringSecondAppTest.account.AccountRepository;
import com.example.SpringSecondAppTest.configuration.JwtService;
import com.example.SpringSecondAppTest.exception.ErrorMessage;
import com.example.SpringSecondAppTest.exception.custom.FamilyNotFoundException;
import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.family.FamilyRepository;
import com.example.SpringSecondAppTest.user.Role;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final FamilyRepository familyRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<Object> register(RegisterRequest request) {
        if (accountRepository.findByLogin(request.login()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ErrorMessage.LOGIN_EXIST);
        }

        Account account = createAccount(request);
        Family family = getOrCreateFamily(request);
        User user = createUser(request, account, family);
        family.addUserToFamily(user);
        familyRepository.save(family);

        String jwtToken = jwtService.generateToken(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthenticateResponse.builder()
                        .token(jwtToken)
                        .build());
    }

    private User createUser(RegisterRequest request, Account account, Family family) {
        User user = User.builder()
                .name(request.firstName())
                .account(account)
                .role(Role.ROLE_USER)
                .currentFamily(family)
                .build();
        return userRepository.save(user);
    }

    private Family getOrCreateFamily(RegisterRequest request) {
        return switch (request.familyCreationType()) {
            case CREATE_NEW -> familyRepository.save(new Family(request.familyName()));
            case JOIN_EXISTING_BY_ID -> familyRepository.findById(request.id())
                    .orElseThrow(() -> new FamilyNotFoundException(ErrorMessage.FAMILY_NOT_FOUND));
        };
    }

    private Account createAccount(RegisterRequest request) {
        Account account = Account.builder()
                .login(request.login())
                .password(passwordEncoder.encode(request.password()))
                .build();
        return accountRepository.save(account);
    }

    public ResponseEntity<Object> authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));
        Optional<User> userOptional = userRepository.findUserByAccountLogin(request.getLogin());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("can not find user");
        }
        User user = userOptional.get();
        String jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(AuthenticateResponse.builder()
                .token(jwtToken)
                .build());
    }
}
