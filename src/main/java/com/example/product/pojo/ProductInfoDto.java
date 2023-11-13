package com.example.product.pojo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductInfoDto {
    private String title;
    private BigDecimal price;
}
