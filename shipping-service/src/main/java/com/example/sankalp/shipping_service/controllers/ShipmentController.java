package com.example.sankalp.shipping_service.controllers;

import com.example.sankalp.shipping_service.clients.OrdersFeignClient;
import com.example.sankalp.shipping_service.dto.ShipmentDto;
import com.example.sankalp.shipping_service.entities.enums.ShipmentStatus;
import com.example.sankalp.shipping_service.services.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShippingService shippingService;
    private final OrdersFeignClient ordersFeignClient;


    @GetMapping("/fetchOrders")
    public ResponseEntity<String> fetchFromOrderService(){
        return ResponseEntity.ok(ordersFeignClient.helloOrders());
    }

    @PostMapping("/createShipment/{orderId}")
    public void createShipment(@PathVariable Long orderId){
        shippingService.createShipment(orderId);
    }

    @PutMapping("/updateShipment")
    public String updateShipmentDetails(@RequestBody ShipmentDto shipmentDto){
        return shippingService.updateShipmentDetails(shipmentDto);
    }



}
