package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.dto.request.ShortOrderDTO;
import com.vorstu.DeliveryServiceBackend.messages.OrderMessage;
import com.vorstu.DeliveryServiceBackend.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@RestController
@RequestMapping("api/customer")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @GetMapping
    public ResponseEntity getCustomerInfo(Principal principal){
        return ResponseEntity.ok().body(
                customerService.getCustomerInfo(principal.getName())
        );
    }

    @GetMapping("order")
    public ResponseEntity getAllOrders(Principal principal){
        return ResponseEntity.ok().body(
                customerService.getCustomerOrders(principal.getName())
        );
    }

    @GetMapping("order/current")
    public ResponseEntity getCurrentOrder(Principal principal){
        return ResponseEntity.ok().body(
                customerService.getCurrentOrder(principal.getName())
        );
    }

    @GetMapping("order/{orderId}")
    public ResponseEntity getOrder(Principal principal,
                                   @PathVariable Long orderId){
            return ResponseEntity.ok().body(
                    customerService.getOrder(principal.getName(), orderId)
            );
    }

    @PutMapping("order/current")
    public ResponseEntity updateCurrentOrder(Principal principal,
                                          @RequestBody ShortOrderDTO order){
        return ResponseEntity.ok().body(
                customerService.updateCurrentOrder(principal.getName(), order)
        );
    }

    @MessageMapping("customer/order/{orderId}")
    @SendTo({"/order/placed", "/order/{orderId}"})
    public OrderMessage makeOrder(Principal principal, @DestinationVariable Long orderId, OrderAction action){
        return customerService.doAction(principal.getName(), orderId, action);
    }

    @GetMapping("address")
    public ResponseEntity getAddresses(Principal principal){
        return ResponseEntity.ok().body(
                customerService.getAddresses(principal.getName())
        );
    }

    @PostMapping("address")
    public ResponseEntity getAddresses(Principal principal, @RequestBody String address){
        return ResponseEntity.ok().body(
                customerService.createAddress(principal.getName(), address)
        );
    }
}
