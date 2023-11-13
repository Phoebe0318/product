package com.example.product.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Schema(name = "ProductInfoDto", description = "Product information with title and price")
public class ProductInfoDto {

    @Schema(description = "Title of the product", example = "Smartphone")
    private String title;

    @Schema(description = "Price of the product", example = "499.99")
    private BigDecimal price;
}
