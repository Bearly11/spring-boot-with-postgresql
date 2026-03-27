package com.springproject.store.services;

import com.springproject.store.exceptions.BadRequestException;
import com.springproject.store.exceptions.NotFoundException;

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

    public Product createProduct(Product product){
        if(_productRepository.existsByProductName(product.getProductName())){
            throw new BadRequestException("Product are already exist");
        }
        return _productRepository.save(product);
    }

    public Product getById(Long id){
        return _productRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Product not found"));
    }

    public List<Product> getProductByName(String productName){
        return _productRepository.findByProductNameContaining(productName);
    }

    public Product updateProductById(Product newProduct,Long id){
        return _productRepository.findById(id).map(product -> {
            if(_productRepository.existsByProductName(newProduct.getProductName())){
                throw new BadRequestException("Product are already exist");
            }


            product.setProductName(newProduct.getProductName());
            product.setPrice(newProduct.getPrice());
            product.setStock(newProduct.getStock());
            return _productRepository.save(product);

        }).orElseThrow(()->
                new NotFoundException("Product not found id:"+ id)
                );

    }

    public void deleteProduct(Long id){
        if(!_productRepository.existsById(id)){
            throw new NotFoundException("Product not found id: "+id);
        }

        _productRepository.deleteById(id);
    }


}
