package com.springproject.store.services;

import com.springproject.store.models.Product;
import com.springproject.store.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository _productRepository;

    public ProductService(ProductRepository productRepository) {
        this._productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        var products = this._productRepository.findAll();
        return products;
    }
}
