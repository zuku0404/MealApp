package com.example.SpringSecondAppTest.user_family;

import com.example.SpringSecondAppTest.family.Family;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFamilyRepository extends JpaRepository<UserFamily, Long> {

    @Query("select uf.family from UserFamily uf " +
            "where uf.familyMember.id = :userId")
    List<Family> findFamiliesByUserId(@Param("userId") Long userId);

    @Query("select uf.family from UserFamily uf " +
            "where uf.familyMember.id = :userId and uf.family.id = :familyId " )
    Optional<Family> findFamilyByUserIdAndFamilyId(@Param("userId") Long userId,@Param("familyId") Long familyId);

    @Modifying
    @Query("delete from UserFamily uf " +
            "where uf.familyMember.id = :userId and uf.family.id = :familyId " )
    void deleteFamilyByUserIdAndFamilyId(@Param("userId") Long userId,@Param("familyId") Long familyId);
}
