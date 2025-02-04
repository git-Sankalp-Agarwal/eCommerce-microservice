package com.example.sankalp.shipping_service.services;

import com.example.sankalp.shipping_service.Repository.ShipmentRepository;
import com.example.sankalp.shipping_service.clients.OrdersFeignClient;
import com.example.sankalp.shipping_service.dto.ShipmentDto;
import com.example.sankalp.shipping_service.entities.Shipment;
import com.example.sankalp.shipping_service.entities.enums.Carrier;
import com.example.sankalp.shipping_service.entities.enums.ShipmentStatus;
import com.example.sankalp.shipping_service.entities.enums.ShippingMethod;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ShippingService {

    private final ShipmentRepository shipmentRepository;
    private final ModelMapper mapper;
    private final OrdersFeignClient ordersFeignClient;

    @Transactional
    public void createShipment(Long orderId) {
        Shipment shipment = Shipment.builder()
                                    .shipmentStatus(ShipmentStatus.PENDING)
                                    .carrier(Carrier.DTDC)
                                    .orderId(orderId)
                                    .shippingMethod(ShippingMethod.EXPRESS)
                                    .build();

        shipment.setTrackingNumber(generateTrackingNo());

        Shipment savedShipment = shipmentRepository.save(shipment);


    }

    @Transactional
    public String updateShipmentDetails(Long shipmentId, @RequestBody ShipmentStatus shipmentStatus ){

        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow();
        switch (shipmentStatus) {
            case SHIPPED -> {
                shipment.setShipmentStatus(shipmentStatus);
                shipment.setShippedDate(LocalDateTime.now());
            }
            case OUT_FOR_DELIVERY -> {
                shipment.setShipmentStatus(shipmentStatus);
                shipment.setOutForDeliveryDate(LocalDateTime.now());
            }
            case DELIVERED -> {
                shipment.setShipmentStatus(shipmentStatus);
                shipment.setActualDeliveryDate(LocalDateTime.now());

            }
            case CANCELLED -> {
                shipment.setShipmentStatus(shipmentStatus);
            }
        }
        Shipment savedShipment = shipmentRepository.save(shipment);

        return ordersFeignClient.updateOrderShipmentDetails(mapper.map(shipment, ShipmentDto.class));
    }

    public String generateTrackingNo(){
        Random random = new Random();
        int randomNumber = 1000000 + random.nextInt(9000000);
        return "TN"+randomNumber;
    }

}
