package com.myshopexample.controller;

import com.myshopexample.annotation.CheckProductRole;
import com.myshopexample.model.dto.product.ProductDto;
import com.myshopexample.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductsController {
    @Autowired
    private ProductsService productsService;
    @PostMapping("/save-new-product")
    @CheckProductRole
    public ProductDto saveNewProduct(
            @RequestBody ProductDto productDto
    ){
        return productsService.saveProduct(productDto);
    }

    @PutMapping("/update-product")
    @CheckProductRole
    public ProductDto updateProduct(
            @RequestBody ProductDto productDto
    ){
        return productsService.updateProduct(productDto);
    }
}
