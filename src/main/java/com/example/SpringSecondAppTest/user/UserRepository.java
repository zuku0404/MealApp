package com.example.SpringSecondAppTest.user;

import com.example.SpringSecondAppTest.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.account = ?1")
    Optional<User> findUserByAccount(Account account);

    @Query("select u from User u " +
            "left join fetch u.account a " +
            "where a.login = :login")
    Optional<User> findUserByAccountLogin(@Param("login") String login);
}
