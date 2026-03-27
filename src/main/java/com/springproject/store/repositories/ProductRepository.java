package com.springproject.store.repositories;

import com.springproject.store.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findProductById(Long id);

    boolean existsByProductName(String productName);


    List<Product> findByProductNameContaining(String productName);

    boolean existsByProductNameAndIdNot(String productName, Long id);

    List<Product> id(Long id);
}
