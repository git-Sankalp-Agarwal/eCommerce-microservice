package com.example.sankalp.ecommerce.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestItemDto {
    private Long id;
    private Long productId;
    private Integer quantity;
}
