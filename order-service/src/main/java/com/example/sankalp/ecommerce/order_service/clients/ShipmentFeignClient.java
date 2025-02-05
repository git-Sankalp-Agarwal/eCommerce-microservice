package com.example.sankalp.ecommerce.order_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "shipping-service",path = "/shipments")
public interface ShipmentFeignClient {

    @PostMapping("/core/createShipment/{orderId}")
    void createShipment(@PathVariable Long orderId);
}
