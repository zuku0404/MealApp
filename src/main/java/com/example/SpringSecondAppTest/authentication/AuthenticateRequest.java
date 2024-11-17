package com.example.SpringSecondAppTest.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class
AuthenticateRequest {
    private String login;
    private String password;
}
