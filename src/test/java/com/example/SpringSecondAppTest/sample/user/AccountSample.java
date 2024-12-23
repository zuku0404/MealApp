package com.example.SpringSecondAppTest.sample.user;

import com.example.SpringSecondAppTest.account.Account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountSample {
    private AccountSample(){}

    public static final List<Account> ACCOUNTS = new ArrayList<>(Arrays.asList(
            new Account(1L, "admin","admin"),
            new Account(2L, "user1","user1"),
            new Account(3L, "user2","user2"),
            new Account(4L, "user3","user3")
    ));
}