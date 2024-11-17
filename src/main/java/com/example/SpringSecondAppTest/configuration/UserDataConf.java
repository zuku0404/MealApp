//package com.example.SpringSecondAppTest.configuration;
//
//
//import com.example.SpringSecondAppTest.account.Account;
//import com.example.SpringSecondAppTest.account.AccountRepository;
//import com.example.SpringSecondAppTest.user.Role;
//import com.example.SpringSecondAppTest.user.User;
//import com.example.SpringSecondAppTest.user.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class UserDataConf {
//
//    @Bean
//    CommandLineRunner initUsers(UserRepository userRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//                // Tworzenie konta administratora
////            Account account1 = new Account();
////            account1.setLogin("admin");
////            account1.setPassword(passwordEncoder.encode("admin"));
////            accountRepository.save(account1);
////            User admin = new User();
////            admin.setName("administrator");
////            admin.setAccount(account1);
////            admin.setRole(Role.ROLE_ADMIN);
////            userRepository.save(admin);
////
////            Account account2 = new Account();
////            account2.setLogin("user1");
////            account2.setPassword(passwordEncoder.encode("user1"));
////            accountRepository.save(account2);
////            User user1 = new User();
////            user1.setName("user1");
////            user1.setAccount(account2);
////            user1.setRole(Role.ROLE_USER);
////            userRepository.save(user1);
//
//            Account account3 = new Account();
//            account3.setLogin("user2");
//            account3.setPassword(passwordEncoder.encode("user2"));
//            accountRepository.save(account3);
//            User user2 = new User();
//            user2.setName("user2");
//            user2.setAccount(account3);
//            user2.setRole(Role.ROLE_USER);
//            userRepository.save(user2);
//
//            Account account4 = new Account();
//            account4.setLogin("user3");
//            account4.setPassword(passwordEncoder.encode("user3"));
//            accountRepository.save(account4);
//            User user3 = new User();
//            user3.setName("user3");
//            user3.setAccount(account4);
//            user3.setRole(Role.ROLE_USER);
//            userRepository.save(user3);
//        };
//    }
//}
