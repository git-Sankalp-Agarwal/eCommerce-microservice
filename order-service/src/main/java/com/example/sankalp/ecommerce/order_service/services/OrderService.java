package com.example.sankalp.ecommerce.order_service.services;

import com.example.sankalp.ecommerce.order_service.clients.InventoryFeintClient;
import com.example.sankalp.ecommerce.order_service.clients.ShipmentFeignClient;
import com.example.sankalp.ecommerce.order_service.dto.OrderRequestDto;
import com.example.sankalp.ecommerce.order_service.entity.OrderItem;
import com.example.sankalp.ecommerce.order_service.entity.OrderStatus;
import com.example.sankalp.ecommerce.order_service.entity.Orders;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.sankalp.ecommerce.order_service.repository.OrdersRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrdersRepository orderRepository;
    private final ModelMapper modelMapper;
    private final InventoryFeintClient inventoryFeintClient;
    private final ShipmentFeignClient shipmentFeignClient;

    final Double DELIVERY_CHARGES  = 5.0;


    public List<OrderRequestDto> getAllOrders() {
        log.info("Fetching all orders");
        List<Orders> orders = orderRepository.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderRequestDto.class)).toList();
    }

    public OrderRequestDto getOrderById(Long id) {
        log.info("Fetching order with ID: {}", id);
        Orders order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return modelMapper.map(order, OrderRequestDto.class);
    }

    @Transactional
    public OrderRequestDto createNewOrder(OrderRequestDto orderRequestDto) {
        log.info("Calling the createOrder method");

        Double totalPrice = inventoryFeintClient.reduceStocks(orderRequestDto);

        BigDecimal finalPrice = BigDecimal.valueOf(totalPrice+DELIVERY_CHARGES);

        orderRequestDto.setTotalPrice(finalPrice);

        Orders order = modelMapper.map(orderRequestDto, Orders.class);

        for(OrderItem orderItem: order.getItems()) {
            orderItem.setOrder(order);
        }
        order.setOrderStatus(OrderStatus.CONFIRMED);

        Orders savedOrder = orderRepository.save(order);
        shipmentFeignClient.createShipment(savedOrder.getId());
        log.info(savedOrder.toString());
        return modelMapper.map(order, OrderRequestDto.class);

    }


    @Transactional
    public String cancelOrder(Long orderId) {
        log.info("Calling the cancelOrder method");

        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with Id"+  orderId));

        if(!order.getOrderStatus().equals(OrderStatus.CONFIRMED)){
            throw new RuntimeException("Order cannot be cancelled");
        }

        OrderRequestDto orderRequestDto = modelMapper.map(order, OrderRequestDto.class);

        inventoryFeintClient.increaseStocks(orderRequestDto);

        order.setOrderStatus(OrderStatus.CANCELLED);
        Orders savedOrder = orderRepository.save(order);

        return "Order has been Cancelled Successfully";

    }



}
