package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.services.AssemblerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
@RestController
@RequestMapping("api/assembler")
public class AssemblerController {

    @Autowired
    AssemblerService assemblerService;
    @GetMapping("order")
    ResponseEntity getOrders(){
        return ResponseEntity.ok().body(assemblerService.getOrders());
    }

    @GetMapping("order/{orderId}/action/{action}")
    ResponseEntity doActionOnOrder(Principal principal,
                                   @PathVariable Long orderId,
                                   @PathVariable OrderAction action){
        assemblerService.doActionOnOrder(principal.getName(), orderId, action);
        return ResponseEntity.ok().build();
    }
}
