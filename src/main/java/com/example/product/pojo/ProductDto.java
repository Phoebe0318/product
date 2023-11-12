package com.example.product.pojo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
}