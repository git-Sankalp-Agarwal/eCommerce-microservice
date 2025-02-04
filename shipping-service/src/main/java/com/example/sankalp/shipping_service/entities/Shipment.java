package com.example.sankalp.shipping_service.entities;

import com.example.sankalp.shipping_service.entities.enums.Carrier;
import com.example.sankalp.shipping_service.entities.enums.ShippingMethod;
import com.example.sankalp.shipping_service.entities.enums.ShipmentStatus;
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
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long orderId;

    @Enumerated(value = EnumType.ORDINAL)
    private Carrier carrier;

    @Column(unique = true)
    private String trackingNumber;

    @Enumerated(value = EnumType.STRING)
    private ShipmentStatus shipmentStatus;

    @Enumerated(value = EnumType.STRING)
    private ShippingMethod shippingMethod;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private LocalDateTime shippedDate;

    private LocalDateTime outForDeliveryDate;

    private LocalDateTime estimatedDeliveryDate;

    private LocalDateTime actualDeliveryDate;

}
