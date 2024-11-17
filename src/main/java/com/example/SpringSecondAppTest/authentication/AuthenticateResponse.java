package com.example.SpringSecondAppTest.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticateResponse {
    private String token;
}
