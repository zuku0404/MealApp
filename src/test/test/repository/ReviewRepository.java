package com.example.SpringSecondAppTest.test.repository;

import com.example.SpringSecondAppTest.test.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
}
