package com.example.SpringSecondAppTest.user_family;

import com.example.SpringSecondAppTest.family.Family;
import com.example.SpringSecondAppTest.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_family", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id, family_id"})
})
public class UserFamily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User familyMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id", nullable = false)
    private Family family;

    public UserFamily(User familyMember, Family family){
        this.familyMember = familyMember;
        this.family = family;
    }
}
