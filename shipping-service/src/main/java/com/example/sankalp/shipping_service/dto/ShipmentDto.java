package com.example.sankalp.shipping_service.dto;

import com.example.sankalp.shipping_service.entities.enums.Carrier;
import com.example.sankalp.shipping_service.entities.enums.ShipmentStatus;
import com.example.sankalp.shipping_service.entities.enums.ShippingMethod;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDto {

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
