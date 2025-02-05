package com.example.sankalp.ecommerce.order_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentDetails {

    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Carrier carrier;

    @Column(unique = true)
    private String trackingNumber;

    @Enumerated(value = EnumType.STRING)
    private ShippingMethod shippingMethod;

    private LocalDateTime shippedDate;

    private LocalDateTime outForDeliveryDate;

    private LocalDateTime estimatedDeliveryDate;

    private LocalDateTime actualDeliveryDate;

}
