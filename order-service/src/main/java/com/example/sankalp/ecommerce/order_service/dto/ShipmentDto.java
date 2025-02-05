package com.example.sankalp.ecommerce.order_service.dto;

import com.example.sankalp.ecommerce.order_service.entity.Carrier;
import com.example.sankalp.ecommerce.order_service.entity.ShipmentStatus;
import com.example.sankalp.ecommerce.order_service.entity.ShippingMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDto {

    private Long id;

    private Long orderId;

    private Carrier carrier;

    private String trackingNumber;

    private ShipmentStatus shipmentStatus;

    private ShippingMethod shippingMethod;

    private LocalDateTime shippedDate;

    private LocalDateTime outForDeliveryDate;

    private LocalDateTime estimatedDeliveryDate;

    private LocalDateTime actualDeliveryDate;

}
