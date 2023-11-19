package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.dto.request.ShortOrderDTO;
import com.vorstu.DeliveryServiceBackend.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("order/{orderId}/action/{action}")
    public ResponseEntity doActionOnOrder(Principal principal,
                                          @PathVariable Long orderId,
                                          @PathVariable OrderAction action){
        try{
            customerService.doAction(principal.getName(), orderId, action);
            return ResponseEntity.ok().build();
        } catch(NoSuchElementException ex){
            log.warn(ex.getMessage());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("address")
    public ResponseEntity getAddresses(Principal principal){
        return ResponseEntity.ok().body(
                customerService.getAddresses(principal.getName())
        );
    }
}
