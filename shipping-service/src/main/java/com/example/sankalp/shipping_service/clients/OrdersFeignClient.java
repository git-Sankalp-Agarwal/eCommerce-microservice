package com.example.sankalp.shipping_service.clients;

import com.example.sankalp.shipping_service.dto.ShipmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="order-service", path = "/orders")
public interface OrdersFeignClient {

    @GetMapping("/core/helloOrders")
    String helloOrders();

    @PutMapping("/core/updateOrder")
    String updateOrderShipmentDetails(@RequestBody ShipmentDto shipmentDto);

}
