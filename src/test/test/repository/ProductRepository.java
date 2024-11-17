package com.example.SpringSecondAppTest.test.repository;

import com.example.SpringSecondAppTest.test.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select distinct p from Product p " +
            "left join fetch p.reviews")
    List<Product> findAllProducts();

//    @Query("select distinct p from Product p " +
//            "left join fetch p.reviews " +
//            "where p.id = :id")
//    @Override
//    Optional<Product> findById(@Param("id") Long id);
}
