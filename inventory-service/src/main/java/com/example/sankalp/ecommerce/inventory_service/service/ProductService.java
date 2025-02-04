package com.example.sankalp.ecommerce.inventory_service.service;


import com.example.sankalp.ecommerce.inventory_service.dto.OrderRequestDto;
import com.example.sankalp.ecommerce.inventory_service.dto.OrderRequestItemDto;
import com.example.sankalp.ecommerce.inventory_service.dto.ProductDto;
import com.example.sankalp.ecommerce.inventory_service.entity.Product;
import com.example.sankalp.ecommerce.inventory_service.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto> getAllInventory() {
        log.info("Fetching all inventory items");
        List<Product> inventories = productRepository.findAll();
        return inventories.stream()
                          .map(product -> modelMapper.map(product, ProductDto.class))
                          .toList();
    }

    public ProductDto getProductById(Long id) {
        log.info("Fetching Product with ID: {}", id);
        Optional<Product> inventory = productRepository.findById(id);
        return inventory.map(item -> modelMapper.map(item, ProductDto.class))
                        .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    @Transactional
    public Double reduceItemsStock(@RequestBody OrderRequestDto orderRequestDto) {

        log.info("Reducing the stocks");

        Double totalPrice = 0.0;

        for (OrderRequestItemDto requestItem : orderRequestDto.getItems()) {
            Long productId = requestItem.getProductId();

            Product product = productRepository.findById(productId)
                                               .orElseThrow(() -> new RuntimeException("Product not found with Id" + requestItem.getProductId()));

            Integer quantity = requestItem.getQuantity();

            if(product.getStock()<quantity){
                throw new RuntimeException("Not enough quantity available");
            }

            product.setStock(product.getStock()-quantity);
            productRepository.save(product);
            totalPrice+=(product.getPrice()*quantity);
        }

        return totalPrice;

    }

    public void increaseItemStocks(OrderRequestDto orderRequestDto) {
        log.info("Increasing the stocks");

        for (OrderRequestItemDto requestItem : orderRequestDto.getItems()) {
            Long productId = requestItem.getProductId();

            Product product = productRepository.findById(productId)
                                               .orElseThrow(() -> new RuntimeException("Product not found with Id" + requestItem.getProductId()));

            Integer quantity = requestItem.getQuantity();


            product.setStock(product.getStock()+quantity);
            productRepository.save(product);
        }

    }
}













