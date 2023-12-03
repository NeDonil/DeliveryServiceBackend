package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.messages.OrderMessage;
import com.vorstu.DeliveryServiceBackend.services.CourierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/courier")
@Slf4j
public class CourierController {
    @Autowired
    CourierService courierService;
    @GetMapping("order")
    ResponseEntity getOrders(){
        return ResponseEntity.ok().body(courierService.getOrders());
    }

    @MessageMapping("courier/order/{orderId}")
    @SendTo({"/order/assembled", "/order/delivering", "/order/delivered", "/order/{orderId}"})
    public OrderMessage makeOrder(Principal principal, @DestinationVariable Long orderId, OrderAction action){
        try{
            return courierService.doAction(principal.getName(), orderId, action);
        } catch(NoSuchElementException ex){
            log.warn(ex.getMessage());
        }
        return new OrderMessage();
    }
}
