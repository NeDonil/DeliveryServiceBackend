package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.dto.ShortOrderDTO;
import com.vorstu.DeliveryServiceBackend.db.dto.ShortProductDTO;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/assembler")
public class AssemblerController {

    @Autowired
    OrderRepository orderRepository;
    @GetMapping("order")
    ResponseEntity getOrders(){
        List<ShortOrderDTO> shortOrders = orderRepository.findAllOrdersByStatus(OrderStatus.PLACED)
                .stream()
                .map(ShortOrderDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(shortOrders);
    }
}
