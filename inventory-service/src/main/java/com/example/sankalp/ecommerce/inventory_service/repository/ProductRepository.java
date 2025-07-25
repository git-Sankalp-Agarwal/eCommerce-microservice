package com.example.sankalp.ecommerce.inventory_service.repository;

import com.example.sankalp.ecommerce.inventory_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
