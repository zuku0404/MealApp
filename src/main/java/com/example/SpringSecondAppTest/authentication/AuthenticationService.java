package com.example.SpringSecondAppTest.authentication;


import com.example.SpringSecondAppTest.account.Account;
import com.example.SpringSecondAppTest.account.AccountRepository;
import com.example.SpringSecondAppTest.configuration.JwtService;
import com.example.SpringSecondAppTest.user.Role;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.user.UserRepository;
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
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<Object> register(RegisterRequest request) {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(Validator.loginValidate(request.getLogin()));
//        stringBuilder.append(Validator.passwordValidate(request.getPassword()));
//        if (!stringBuilder.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(stringBuilder.toString());
//        }
        if (accountRepository.findByLogin(request.getLogin()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("login already exists");
        }
        Account account = Account.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        accountRepository.save(account);

        User user = User.builder()
                .name(request.getFirstName())
                .account(account)
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthenticateResponse.builder()
                        .token(jwtToken)
                        .build());
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
