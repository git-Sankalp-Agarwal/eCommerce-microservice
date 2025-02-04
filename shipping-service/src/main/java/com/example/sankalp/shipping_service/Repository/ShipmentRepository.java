package com.example.sankalp.shipping_service.Repository;

import com.example.sankalp.shipping_service.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
