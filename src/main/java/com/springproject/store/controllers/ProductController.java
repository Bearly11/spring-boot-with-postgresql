package com.springproject.store.controllers;


import com.springproject.store.models.Product;
import com.springproject.store.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("/api/v1/products/")
public class ProductController {
    private final ProductService _productService;

    public ProductController(ProductService productService) {
        _productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllProducts(){
        var products = _productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.ok(_productService.createProduct(product));

    }

    @GetMapping("{id}")
    public  ResponseEntity<Object> getProductById(@PathVariable Long id){
        var product = _productService.getById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("search")
    public ResponseEntity<Object> getByProductName(String productName){
        return ResponseEntity.ok(_productService.getProductByName(productName));
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product,
                                                @PathVariable Long id){
        var newProduct = _productService.updateProductById(product,id);
        return ResponseEntity.ok(newProduct);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        _productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
