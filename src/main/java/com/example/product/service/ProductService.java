package com.example.product.service;

import com.example.product.dao.repository.ProductRepository;
import com.example.product.pojo.ProductDo;
import com.example.product.pojo.ProductDto;
import com.example.product.pojo.ProductInfoDto;
import com.example.product.pojo.ResponseDto;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final MessageSource messageSource;

    public ResponseDto<String> createProduct(ProductDto productDto) {
        log.info("Creating product with name: {}", productDto.getName());

        if (StringUtils.isBlank(productDto.getName()) || productDto.getPrice() == null) {
            log.warn("Invalid input for creating product.");
            ResponseDto<String> errorResponse = new ResponseDto<>();
            errorResponse.setStatus(0);
            String errorMessage = messageSource.getMessage("product.create.invalidInput", null, LocaleContextHolder.getLocale());
            errorResponse.setMessage(errorMessage);
            return errorResponse;
        }

        if (productRepository.existsByTitle(productDto.getName())) {
            log.warn("Product with name {} already exists.", productDto.getName());
            ResponseDto<String> errorResponse = new ResponseDto<>();
            errorResponse.setStatus(0);
            String errorMessage = messageSource.getMessage("product.create.duplicateName", null, LocaleContextHolder.getLocale());
            errorResponse.setMessage(errorMessage);
            return errorResponse;
        }

        ProductDo productDo = mapToProductDo(productDto);
        productRepository.save(productDo);

        log.info("Product with name {} created successfully.", productDto.getName());

        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setStatus(1);
        String successMessage = messageSource.getMessage("product.create.success", null, LocaleContextHolder.getLocale());
        responseDto.setMessage(successMessage);

        return responseDto;
    }

    public ResponseDto<String> editProduct(Integer productId, ProductDto productDto) {
        log.info("Editing product with ID: {}", productId);

        Optional<ProductDo> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            ProductDo existingProduct = optionalProduct.get();

            existingProduct.setId(productId);
            existingProduct.setTitle(productDto.getName());
            existingProduct.setDescription(productDto.getDescription());
            existingProduct.setPrice(productDto.getPrice());
            existingProduct.setStockQuantity(productDto.getStockQuantity());
            existingProduct.setPublish(productDto.getPublish());

            productRepository.save(existingProduct);

            log.info("Product with ID {} edited successfully.", productId);

            ResponseDto<String> responseDto = new ResponseDto<>();
            responseDto.setStatus(1);
            String message = messageSource.getMessage("product.edit.success", null, LocaleContextHolder.getLocale());
            responseDto.setMessage(message);

            return responseDto;
        } else {
            log.warn("Product with ID {} not found for editing.", productId);

            ResponseDto<String> responseDto = new ResponseDto<>();
            responseDto.setStatus(0);
            String message = messageSource.getMessage("product.edit.not.found", null, LocaleContextHolder.getLocale());
            responseDto.setMessage(message);

            return responseDto;
        }
    }

    public ResponseDto<String> deleteProduct(Integer productId) {
        log.info("Deleting product with ID: {}", productId);

        Optional<ProductDo> existingProductOptional = productRepository.findById(productId);

        if (existingProductOptional.isPresent()) {
            productRepository.deleteById(productId);

            log.info("Product with ID {} deleted successfully.", productId);

            ResponseDto<String> responseDto = new ResponseDto<>();
            responseDto.setStatus(1);
            String message = messageSource.getMessage("product.delete.success", null, LocaleContextHolder.getLocale());
            responseDto.setMessage(message);

            return responseDto;
        } else {
            log.warn("Product with ID {} not found for deletion.", productId);

            ResponseDto<String> responseDto = new ResponseDto<>();
            responseDto.setStatus(0);
            String message = messageSource.getMessage("product.delete.not.found", null, LocaleContextHolder.getLocale());
            responseDto.setMessage(message);

            return responseDto;
        }
    }

    public ResponseDto<List<ProductInfoDto>> getAllProducts() {
        try {
            List<ProductDo> allProducts = productRepository.findAll();

            log.info("Retrieved {} products from the database.", allProducts.size());

            List<ProductInfoDto> productInfoList = mapToProductInfoDtoList(allProducts);

            ResponseDto<List<ProductInfoDto>> responseDto = new ResponseDto<>();
            responseDto.setStatus(1);
            String message = messageSource.getMessage("product.fetch.success", null, LocaleContextHolder.getLocale());
            responseDto.setMessage(message);
            responseDto.setData(productInfoList);

            return responseDto;
        } catch (Exception e) {
            log.error("Error occurred while fetching products: {}", e.getMessage(), e);

            ResponseDto<List<ProductInfoDto>> errorResponse = new ResponseDto<>();
            errorResponse.setStatus(0);
            String errorMessage = messageSource.getMessage("product.fetch.error", null, LocaleContextHolder.getLocale());
            errorResponse.setMessage(errorMessage);

            return errorResponse;
        }
    }

    public ResponseDto<List<ProductInfoDto>> getAllPublishedProducts() {
        try {
            List<ProductDo> publishedProducts = productRepository.findByPublish(1);

            List<ProductInfoDto> productInfoList = mapToProductInfoDtoList(publishedProducts);

            ResponseDto<List<ProductInfoDto>> responseDto = new ResponseDto<>();
            responseDto.setStatus(1);
            String message = messageSource.getMessage("product.fetch.success", null, LocaleContextHolder.getLocale());
            responseDto.setMessage(message);
            responseDto.setData(productInfoList);

            log.info("Successfully fetched all published products.");

            return responseDto;
        } catch (Exception e) {
            log.error("Error while fetching all published products: {}", e.getMessage());
            ResponseDto<List<ProductInfoDto>> errorResponse = new ResponseDto<>();
            errorResponse.setStatus(0);
            String errorMessage = messageSource.getMessage("product.fetch.error", null, LocaleContextHolder.getLocale());
            errorResponse.setMessage(errorMessage);
            return errorResponse;
        }
    }

    public ResponseDto<List<ProductInfoDto>> getAllUnpublishedProducts() {
        try {
            List<ProductDo> unpublishedProducts = productRepository.findByPublish(0);

            List<ProductInfoDto> productInfoList = mapToProductInfoDtoList(unpublishedProducts);

            ResponseDto<List<ProductInfoDto>> responseDto = new ResponseDto<>();
            responseDto.setStatus(1);
            String message = messageSource.getMessage("product.fetch.success", null, LocaleContextHolder.getLocale());
            responseDto.setMessage(message);
            responseDto.setData(productInfoList);

            log.info("Successfully fetched all unpublished products.");

            return responseDto;
        } catch (Exception e) {
            log.error("Error while fetching all unpublished products: {}", e.getMessage());
            ResponseDto<List<ProductInfoDto>> errorResponse = new ResponseDto<>();
            errorResponse.setStatus(0);
            String errorMessage = messageSource.getMessage("product.fetch.error", null, LocaleContextHolder.getLocale());
            errorResponse.setMessage(errorMessage);
            return errorResponse;
        }
    }

    public ProductDo mapToProductDo(ProductDto productDto) {
        ProductDo productDo = new ProductDo();
        productDo.setTitle(productDto.getName());
        productDo.setDescription(productDto.getDescription());
        productDo.setPrice(productDto.getPrice());
        productDo.setStockQuantity(productDto.getStockQuantity());
        productDo.setPublish(productDto.getPublish());

        return productDo;
    }

    private List<ProductInfoDto> mapToProductInfoDtoList(List<ProductDo> products) {
        return products.stream()
                .map(product -> {
                    ProductInfoDto productInfoDto = new ProductInfoDto();
                    productInfoDto.setTitle(product.getTitle());
                    productInfoDto.setPrice(product.getPrice());
                    return productInfoDto;
                })
                .collect(Collectors.toList());
    }
}
