package com.example.SpringSecondAppTest.family;

import com.example.SpringSecondAppTest.family_ingredient.FamilyIngredient;
import com.example.SpringSecondAppTest.preferences.FamilyPreference;
import com.example.SpringSecondAppTest.user.User;
import com.example.SpringSecondAppTest.family_meal.FamilyMeal;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "family")
    private Set<FamilyIngredient> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "family")
    private Set<FamilyMeal> meals = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preference_id")
    private FamilyPreference preference;

    @ManyToMany
    @JoinTable(name = "user_family", joinColumns = @JoinColumn(name = "family_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> familyMembers = new HashSet<>();

    public Family(Long id, String name, FamilyPreference preference){
        this.id = id;
        this.name = name;
        this.preference = preference;
    }

    public Family(String name, Set<User> familyMembers) {
        this.name = name;
        this.familyMembers = familyMembers;
    }

    public Family(String name) {
        this.name = name;
    }

    public void addUserToFamily(User user) {
        familyMembers.add(user);
        user.getFamilies().add(this);
    }

    public void removeUserFromFamily(User user){
        familyMembers.remove(user);
        user.getFamilies().remove(this);
    }
}
