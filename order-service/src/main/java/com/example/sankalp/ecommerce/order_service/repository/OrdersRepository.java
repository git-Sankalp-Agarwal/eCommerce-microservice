package com.example.sankalp.ecommerce.order_service.repository;


import com.example.sankalp.ecommerce.order_service.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
