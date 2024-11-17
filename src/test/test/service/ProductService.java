package com.example.SpringSecondAppTest.test.service;

import com.example.SpringSecondAppTest.test.entity.Product;
import com.example.SpringSecondAppTest.test.entity.dto.ProductDto;
import com.example.SpringSecondAppTest.test.entity.dto.ProductToDtoMapper;
import com.example.SpringSecondAppTest.test.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public List<ProductDto> findAll() {

        return ProductToDtoMapper.mapToDtos(productRepository.findAll());
    }

    public List<Product> findAllProducts() {
        return productRepository.findAllProducts();
    }

    public ProductDto findPostById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return ProductToDtoMapper.mapProductToDto(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}
