//package com.example.SpringSecondAppTest.preferences;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
//
//    @Query("select up from UserPreferences up " +
//            "where up.user.id = user_id")
//    Optional<UserPreference> findPreferenceByUserId(@Param("user_id") Long id);
//}
