package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.messages.OrderMessage;
import com.vorstu.DeliveryServiceBackend.services.AssemblerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/assembler")
@Slf4j
public class AssemblerController {

    @Autowired
    AssemblerService assemblerService;
    @GetMapping("order")
    ResponseEntity getOrders(){
        return ResponseEntity.ok().body(assemblerService.getOrders());
    }

    @GetMapping("order/current")
    ResponseEntity getCurrentOrder(Principal principal){
        return ResponseEntity.ok().body(assemblerService.getCurrentOrder(principal.getName()));
    }

    @MessageMapping("assembler/order/{orderId}")
    @SendTo({"/order/placed", "/order/assembling", "/order/assembled", "/order/{orderId}"})
    public OrderMessage makeOrder(Principal principal, @DestinationVariable Long orderId, OrderAction action){
            return assemblerService.doAction(principal.getName(), orderId, action);
    }
}
