package com.example.product.dao.repository;

import com.example.product.pojo.ProductDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductDo, Integer> {
    List<ProductDo> findByPublish(int i);
}
