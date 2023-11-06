package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.entities.CourierEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.CourierRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.mappers.OrderListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/courier")
public class CourierController {
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderListMapper orderListMapper;
    @GetMapping("order")
    ResponseEntity getOrders(){
        List<OrderEntity> orderEntities = orderRepository.findAllOrdersByStatus(OrderStatus.ASSEMBLED);
        return ResponseEntity.ok().body(orderListMapper.toDTOList(orderEntities));
    }

    @GetMapping("order/{orderId}/action/{action}")
    ResponseEntity doActionOnOrder(Principal principal,
                                   @PathVariable Long orderId,
                                   @PathVariable OrderAction action){
        CourierEntity courier = courierRepository.findUserByEmail(principal.getName());
        OrderEntity orderEntity = orderRepository.findById(orderId).get();

        switch (action) {
            case TO_DELIVERY -> {
                orderEntity.setStatus(OrderStatus.DELIVERING);
                orderEntity.setCourier(courier);
            }
            case TO_DELIVERED -> {
                orderEntity.setEndDate(LocalDateTime.now());
                orderEntity.setStatus(OrderStatus.DELIVERED);
            }
            case REFUSE -> {
                orderEntity.setStatus(OrderStatus.ASSEMBLED);
                orderEntity.setCourier(null);
            }
        }

        orderRepository.save(orderEntity);
        return ResponseEntity.ok().build();
    }
}
