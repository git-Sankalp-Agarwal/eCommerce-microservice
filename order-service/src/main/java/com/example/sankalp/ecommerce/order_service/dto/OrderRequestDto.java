package com.example.sankalp.ecommerce.order_service.dto;

import com.example.sankalp.ecommerce.order_service.entity.ShipmentDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private Long id;
    private List<OrderRequestItemDto> items;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;

    private ShipmentDetailsDto shipmentDetails;

}

