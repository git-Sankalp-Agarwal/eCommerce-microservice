package com.example.sankalp.ecommerce.order_service.controller;

import com.example.sankalp.ecommerce.order_service.clients.InventoryFeintClient;
import com.example.sankalp.ecommerce.order_service.dto.OrderRequestDto;
import com.example.sankalp.ecommerce.order_service.dto.ShipmentDto;
import com.example.sankalp.ecommerce.order_service.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/helloOrders")
    public ResponseEntity<String> helloOrders(@RequestHeader("X-User-Id") Long userId) {
       // Long userId = 232L;
        log.info("User id value::: {}", userId);
        return ResponseEntity.ok("Hi from OrderService with ID::: "+ userId);
    }

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders(HttpServletRequest httpServletRequest) {
        log.info("Fetching all orders via controller");
        List<OrderRequestDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);  // Returns 200 OK with the list of orders
    }

    @GetMapping("/getMyOrderDetails/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id) {
        log.info("Fetching order with ID: {} via controller", id);
        OrderRequestDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);  // Returns 200 OK with the order
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
       return ResponseEntity.ok(orderService.createNewOrder(orderRequestDto));

    }

    @PostMapping("/cancelOrder/{orderId}")
    public ResponseEntity<String> createOrder(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.cancelOrder(orderId));

    }

    @PutMapping("/updateOrder")
    String updateOrderShipmentDetails(@RequestBody ShipmentDto shipmentDto){
        return orderService.updateShipmentDetails(shipmentDto);
    }

}
