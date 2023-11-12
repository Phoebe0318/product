package com.example.product.controller;

import com.example.product.pojo.ProductDto;
import com.example.product.pojo.ResponseDto;
import com.example.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "create product")
    public ResponseDto<String> createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }

    @PutMapping("/{productId}")
    @Operation(summary = "edit product")
    public ResponseDto<String> editProduct(@PathVariable Integer productId, @RequestBody ProductDto productDto){
        return productService.editProduct(productId, productDto);
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "delete product")
    public ResponseDto<String> deleteProduct(@PathVariable Integer productId){
        return productService.deleteProduct(productId);
    }

    @GetMapping
    @Operation(summary = "get all products")
    public ResponseDto<String> getAllProducts(){
        return productService.getAllProducts();
    }
}
