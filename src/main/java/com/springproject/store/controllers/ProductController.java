package com.springproject.store.controllers;


import com.springproject.store.dtos.ProductRequestDto;
import com.springproject.store.dtos.ProductResponseDto;
import com.springproject.store.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService _productService;


    public ProductController(ProductService productService) {
        _productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        return ResponseEntity.ok(_productService.getAllProducts());
    }

    @PostMapping("")
    public ResponseEntity<ProductResponseDto> createProduct(
            @RequestBody @Valid ProductRequestDto dto){
        return ResponseEntity.status(201).body(_productService.createProduct(dto));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id){
        var product = _productService.getById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> getByProductName(@RequestParam String productName){
        return ResponseEntity.ok(_productService.getProductByName(productName));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@Valid @RequestBody ProductRequestDto product,
                                                @PathVariable Long id){
        var newProduct = _productService.updateProductById(product,id);
        return ResponseEntity.ok(newProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        _productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<ProductResponseDto>> getPaginatedProduct(@RequestParam(defaultValue = "0") int page
    ,@RequestParam(defaultValue = "5") int size){
        return ResponseEntity.ok(_productService.getPaginatedProduct(page,size));
    }
}
