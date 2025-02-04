package com.example.sankalp.ecommerce.order_service.clients;

import com.example.sankalp.ecommerce.order_service.dto.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service", path = "/inventory")
public interface InventoryFeintClient {

    @PutMapping("/products/reduceStocks")
    Double reduceStocks(@RequestBody OrderRequestDto orderRequestDto);

    @PutMapping("/products/increaseStocks")
    void increaseStocks(OrderRequestDto orderRequestDto);
}
