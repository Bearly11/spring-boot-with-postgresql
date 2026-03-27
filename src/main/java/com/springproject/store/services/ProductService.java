package com.springproject.store.services;

import com.springproject.store.exceptions.DuplicateProductException;
import com.springproject.store.exceptions.MyResourceNotFoundException;
import com.springproject.store.models.Product;
import com.springproject.store.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            throw new DuplicateProductException("Product name already exists");
        }
        return _productRepository.save(product);
    }

    public Product getById(Long id){
        return _productRepository.findById(id)
                .orElseThrow(() ->
                        new MyResourceNotFoundException("Product not found with id: " + id)
                );
    }

    public List<Product> getProductByName(String productName){
        return _productRepository.findByProductNameContaining(productName);
    }

    public Product updateProductById(Product newProduct,Long id){
        return _productRepository.findById(id).map(product -> {

            if (_productRepository.existsByProductNameAndIdNot(
                    newProduct.getProductName(), id)) {
                throw new DuplicateProductException("Product name already exists");
            }

            product.setProductName(newProduct.getProductName());
            product.setPrice(newProduct.getPrice());
            product.setStock(newProduct.getStock());
            return _productRepository.save(product);

        }).orElseThrow(()->
                new MyResourceNotFoundException("Product not found"));

    }

    public void deleteProduct(Long id){
        if(!_productRepository.existsById(id)){
            throw new MyResourceNotFoundException("Product not found");
        }
        _productRepository.deleteById(id);
    }


}
