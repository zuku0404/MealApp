package com.example.SpringSecondAppTest.user;

import com.example.SpringSecondAppTest.account.Account;
import com.example.SpringSecondAppTest.preferences.UserPreference;
import com.example.SpringSecondAppTest.user_ingredient.UserIngredient;
import com.example.SpringSecondAppTest.user_meal.UserMeal;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Account account;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preference_id")
    private UserPreference preference;

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private Set<UserIngredient> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private Set<UserMeal> meals = new HashSet<>();

    public User(String name, Role role, Account account) {
        this.name = name;
        this.role = role;
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return getAccount().getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", account=" + account +
                '}';
    }
}


//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    @OneToOne(cascade = CascadeType.REMOVE)
//    private Account account;
//
//    @OneToMany(mappedBy = "user")
//    private Set<UserIngredient> ingredients;
//
//    @OneToMany(mappedBy = "user")
//    private Set<UserMeal> meals;
//
//    @OneToMany(mappedBy = "user")
//    private Set<UserCuisinePreference> userCuisinePreferences;
//}
