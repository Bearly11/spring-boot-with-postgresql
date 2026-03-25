package com.springproject.store.controllers;

import com.springproject.store.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/products/")
public class ProductController {
    private final ProductService _productService;

    public ProductController(ProductService productService) {
        _productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(){
        var products = _productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
