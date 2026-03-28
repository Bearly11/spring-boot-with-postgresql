package com.springproject.store.services;

import com.springproject.store.dtos.ProductRequestDto;
import com.springproject.store.dtos.ProductResponseDto;
import com.springproject.store.exceptions.BadRequestException;
import com.springproject.store.exceptions.NotFoundException;

import com.springproject.store.mappers.MapperProduct;
import com.springproject.store.models.Product;
import com.springproject.store.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.awt.print.Pageable;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository _productRepository;
    private final MapperProduct _mapperProduct;


    public ProductService(ProductRepository productRepository, MapperProduct mapperProduct) {

        this._productRepository = productRepository;
        _mapperProduct = mapperProduct;
    }

    public List<ProductResponseDto> getAllProducts(){
        var products = this._productRepository.findAll()
                .stream()
                .map(_mapperProduct::toDto)
                .toList();
        return products;
    }

    public ProductResponseDto createProduct(ProductRequestDto dto){
        if(_productRepository.existsByProductName(dto.getProductName())){
            throw new BadRequestException("Product are already exist");
        }
        Product product = _mapperProduct.toEntity(dto);
        Product saved = _productRepository.save(product);
        return _mapperProduct.toDto(saved);
    }

    public ProductResponseDto getById(Long id){
        Product product = _productRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Product not found"));

        return _mapperProduct.toDto(product);
    }

    public List<ProductResponseDto> getProductByName(String productName){
        var productNames =_productRepository.findByProductNameContaining(productName)
                .stream()
                .map(_mapperProduct::toDto)
                .toList();

        return productNames;
    }

    public ProductResponseDto updateProductById(ProductRequestDto newProduct,Long id){
       Product product = _productRepository.findById(id)
               .orElseThrow(()->
                       new NotFoundException("Product not found"));

       if(_productRepository.existsByProductNameAndIdNot(newProduct.getProductName(), id)){
           throw new BadRequestException("Product name already exists");
       }
       product.setProductName(newProduct.getProductName());
       product.setPrice(newProduct.getPrice());
       product.setStock(newProduct.getStock());

       return _mapperProduct.toDto(_productRepository.save(product));


    }

    public void deleteProduct(Long id){
        if(!_productRepository.existsById(id)){
            throw new NotFoundException("Product not found id: "+id);
        }

        _productRepository.deleteById(id);
    }


    public List<ProductResponseDto> getPaginatedProduct(int page,int size){
        var pageAble= PageRequest.of(page,size);
        var products= _productRepository.findAll(pageAble).getContent()
                .stream()
                .map(_mapperProduct::toDto)
                .toList();
        return products;
    }




}
