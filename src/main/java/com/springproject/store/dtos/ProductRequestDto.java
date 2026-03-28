package com.springproject.store.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProductRequestDto {
    @NotBlank(message = "Product name are required")
    private String productName;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.1",message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Product are required")
    @Min(value = 0,message = "Stock cannot be negative")
    private int stock;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
