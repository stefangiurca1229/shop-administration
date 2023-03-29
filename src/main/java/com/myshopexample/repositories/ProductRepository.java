package com.myshopexample.repositories;

import com.myshopexample.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public Page<Product> findProductsByCategory(String category, Pageable pageable);
}
