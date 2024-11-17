package com.example.SpringSecondAppTest.test.controller;

import com.example.SpringSecondAppTest.test.entity.Product;
import com.example.SpringSecondAppTest.test.entity.dto.ProductDto;
import com.example.SpringSecondAppTest.test.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findProduct(@PathVariable("id") Long id) {
        return productService.findPostById(id);
    }

    @GetMapping("/{id}/reviews")
    public Product findProductWithReviews(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @GetMapping("/reviews")
    public List<Product> productsWithReview() {
        return productService.findAllProducts();
    }
}