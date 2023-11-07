package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.services.CourierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("order/{orderId}/action/{action}")
    ResponseEntity doActionOnOrder(Principal principal,
                                   @PathVariable Long orderId,
                                   @PathVariable OrderAction action){
        try{
            courierService.doActionOnOrder(principal.getName(), orderId, action);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException ex){
            log.warn(ex.getMessage());
        }

        return ResponseEntity.notFound().build();
    }
}
