package com.example.SpringSecondAppTest.user;

import com.example.SpringSecondAppTest.account.Account;
import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.user_family.UserFamily;
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_family_id")
    private Family currentFamily;

    @OneToMany(mappedBy = "familyMember")
    private final Set<UserFamily> familyMembers = new HashSet<>();

    public User(String name, Role role, Account account) {
        this.name = name;
        this.role = role;
        this.account = account;
    }

    public User(String name, Role role, Family currentFamily, Account account) {
        this.name = name;
        this.role = role;
        this.currentFamily = currentFamily;
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
}
