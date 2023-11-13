package com.example.product.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Schema(name = "ProductDto", description = "Product information")
public class ProductDto {
    @Schema(description = "Name of the product", example = "Smartphone")
    private String name;

    @Schema(description = "Description of the product")
    private String description;

    @Schema(description = "Price of the product", example = "499.99")
    private BigDecimal price;

    @Schema(description = "Stock quantity of the product", example = "100")
    private Integer stockQuantity;

    @Schema(description = "Publish status of the product (0 for unpublished, 1 for published)", example = "1")
    private Integer publish;
}
