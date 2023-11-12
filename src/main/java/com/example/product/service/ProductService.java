package com.example.product.service;

import com.example.product.dao.repository.ProductRepository;
import com.example.product.pojo.ProductDo;
import com.example.product.pojo.ProductDto;
import com.example.product.pojo.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final MessageSource messageSource;

    public ResponseDto<String> createProduct(ProductDto productDto) {
        ProductDo productDo = mapToProductDo(productDto);
        productRepository.save(productDo);

        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setStatus(1);
        String message = messageSource.getMessage("product.create.success", null, LocaleContextHolder.getLocale());
        responseDto.setMessage(message);

        return responseDto;
    }

    public ResponseDto<String> editProduct(Integer productId, ProductDto productDto) {
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

            ResponseDto<String> responseDto = new ResponseDto<>();
            responseDto.setStatus(1);
            String message = messageSource.getMessage("product.edit.success", null, LocaleContextHolder.getLocale());
            responseDto.setMessage(message);

            return responseDto;
        } else {
            ResponseDto<String> responseDto = new ResponseDto<>();
            responseDto.setStatus(0);
            String message = messageSource.getMessage("product.edit.notfound", null, LocaleContextHolder.getLocale());
            responseDto.setMessage(message);

            return responseDto;
        }
    }

    public ResponseDto<String> deleteProduct(Integer productId) {
        productRepository.deleteById(productId);

        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setStatus(1);
        String message = messageSource.getMessage("product.delete.success", null, LocaleContextHolder.getLocale());
        responseDto.setMessage(message);

        return responseDto;
    }

    public ResponseDto<String> getAllProducts() {
        List<ProductDo> allProducts = productRepository.findAll();

        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setStatus(1);
        String message = messageSource.getMessage("product.fetch.success", null, LocaleContextHolder.getLocale());
        responseDto.setMessage(message);
        responseDto.setData(allProducts);

        return responseDto;
    }

    public ResponseDto<String> getAllPublishedProducts() {
        List<ProductDo> publishedProducts = productRepository.findByPublish(1);

        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setStatus(1);
        String message = messageSource.getMessage("product.fetch.success", null, LocaleContextHolder.getLocale());
        responseDto.setMessage(message);
        responseDto.setData(publishedProducts);

        return responseDto;
    }

    public ResponseDto<String> getAllUnpublishedProducts() {
        List<ProductDo> unPublishedProducts = productRepository.findByPublish(0);

        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setStatus(1);
        String message = messageSource.getMessage("product.fetch.success", null, LocaleContextHolder.getLocale());
        responseDto.setMessage(message);
        responseDto.setData(unPublishedProducts);

        return responseDto;
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
}
