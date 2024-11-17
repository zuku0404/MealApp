package com.example.SpringSecondAppTest.test.entity.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductDto {
    private Long id;
    private String name;
}
