package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.dto.request.ShortOrderDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.messages.OrderMessage;
import com.vorstu.DeliveryServiceBackend.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;
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
        try{
            return ResponseEntity.ok().body(
                    customerService.getOrder(principal.getName(), orderId)
            );
        } catch(NoSuchElementException ex){
            log.warn(ex.getMessage());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("order/current")
    public ResponseEntity updateCurrentOrder(Principal principal,
                                          @RequestBody ShortOrderDTO order){
        return ResponseEntity.ok().body(
                customerService.updateCurrentOrder(principal.getName(), order)
        );
    }

    @MessageMapping("/customer/make_order")
    @SendTo("/order/placed")
    public OrderMessage makeOrder(Principal principal, Long orderId){
        try{
            return customerService.doAction(principal.getName(), orderId, OrderAction.MAKE);
        } catch(NoSuchElementException ex){
            log.warn(ex.getMessage());
        }
        return new OrderMessage();
    }

    @MessageMapping("/customer/reject_order")
    @SendTo({"/order/placed", "/order/assembly"})
    public OrderMessage rejectOrder(Principal principal, Long orderId){
        try{
            return customerService.doAction(principal.getName(), orderId, OrderAction.REFUSE);
        } catch(NoSuchElementException ex){
            log.warn(ex.getMessage());
        }
        return new OrderMessage();
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
