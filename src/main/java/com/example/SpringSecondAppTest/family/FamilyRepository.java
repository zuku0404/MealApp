package com.example.SpringSecondAppTest.family;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
    @Query("select f from Family f " +
            "left join f.familyMembers fm " +
            "where fm.id = :id")
    List<Family> findByUserId(Long id);
}
