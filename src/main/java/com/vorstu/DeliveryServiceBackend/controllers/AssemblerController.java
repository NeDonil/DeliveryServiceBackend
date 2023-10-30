package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.dto.OrderDTO;
import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.AssemblerRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/assembler")
public class AssemblerController {

    @Autowired
    AssemblerRepository assemblerRepository;
    @Autowired
    OrderRepository orderRepository;
    @GetMapping("order")
    ResponseEntity getOrders(){
        List<OrderDTO.Short.Response> shortOrders = orderRepository.findAllOrdersByStatus(OrderStatus.PLACED)
                .stream()
                .map(OrderDTO.Short.Response::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(shortOrders);
    }

    @GetMapping("order/{orderId}/action/{action}")
    ResponseEntity doActionOnOrder(Principal principal,
                                   @PathVariable Long orderId,
                                   @PathVariable OrderAction action){
        AssemblerEntity assembler = assemblerRepository.findUserByEmail(principal.getName());
        OrderEntity orderEntity = orderRepository.findById(orderId).get();

        switch (action) {
            case TO_ASSEMBLY -> {
                orderEntity.setStatus(OrderStatus.ASSEMBLING);
                orderEntity.setAssembler(assembler);
            }
            case TO_ASSEMBLED -> {
                orderEntity.setStatus(OrderStatus.ASSEMBLED);
            }
            case REFUSE -> {
                orderEntity.setStatus(OrderStatus.PLACED);
                orderEntity.setAssembler(null);

            }
        }

        orderRepository.save(orderEntity);
        return ResponseEntity.ok().build();
    }
}
