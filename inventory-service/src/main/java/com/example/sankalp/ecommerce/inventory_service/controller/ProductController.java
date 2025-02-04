package com.example.sankalp.ecommerce.inventory_service.controller;

import com.example.sankalp.ecommerce.inventory_service.clients.OrdersFeignClient;
import com.example.sankalp.ecommerce.inventory_service.dto.OrderRequestDto;
import com.example.sankalp.ecommerce.inventory_service.dto.ProductDto;
import com.example.sankalp.ecommerce.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final OrdersFeignClient ordersFeignClient;

    @GetMapping("/fetchOrders")
    public ResponseEntity<String> fetchFromOrderService(){
        return ResponseEntity.ok(ordersFeignClient.helloOrders());
    }


    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllInventory() {
        List<ProductDto> inventories = productService.getAllInventory();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id) {
        ProductDto inventory = productService.getProductById(id);
        return ResponseEntity.ok(inventory);
    }

    @PutMapping("/reduceStocks")
    public Double reduceStocks(@RequestBody OrderRequestDto orderRequestDto){
        return productService.reduceItemsStock(orderRequestDto);
    }

    @PutMapping("/increaseStocks")
    public void increaseStocks(@RequestBody OrderRequestDto orderRequestDto){
        productService.increaseItemStocks(orderRequestDto);
    }

}
