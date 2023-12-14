package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.dto.request.FullAssemblerDTO;
import com.vorstu.DeliveryServiceBackend.dto.request.FullCourierDTO;
import com.vorstu.DeliveryServiceBackend.messages.OrderMessage;
import com.vorstu.DeliveryServiceBackend.services.AdminService;
import com.vorstu.DeliveryServiceBackend.services.AssemblerService;
import com.vorstu.DeliveryServiceBackend.services.CourierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/admin")
@Slf4j
public class AdminController {

    @Autowired
    AssemblerService assemblerService;
    @Autowired
    CourierService courierService;

    @Autowired
    AdminService adminService;

    @GetMapping("assembler")
    ResponseEntity getAssemblers(){
        return ResponseEntity.ok().body(
                assemblerService.getAssemblers()
        );
    }

    @PostMapping("assembler")
    ResponseEntity addAssembler(@RequestBody FullAssemblerDTO assembler){
        return ResponseEntity.ok().body(
                assemblerService.createAssembler(assembler)
        );
    }

    @PutMapping("assembler")
    ResponseEntity updateAssembler(@RequestParam Long assemblerId,
                                @RequestBody FullAssemblerDTO assembler){
            return ResponseEntity.ok().body(assemblerService.updateAssembler(assemblerId, assembler));
    }

    @DeleteMapping("assembler")
    ResponseEntity deleteAssembler(@RequestParam Long assemblerId){
        assemblerService.deleteAssembler(assemblerId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("courier")
    ResponseEntity getCouriers(){
        return ResponseEntity.ok().body(
                courierService.getCouriers()
        );
    }

    @PostMapping("courier")
    ResponseEntity addCourier(@RequestBody FullCourierDTO courier){
        return ResponseEntity.ok().body(courierService.createCourier(courier));
    }

    @PutMapping("courier")
    ResponseEntity updateCourier(@RequestParam Long courierId,
                                   @RequestBody FullCourierDTO courier){
            return ResponseEntity.ok().body(courierService.updateCourier(courierId, courier));
    }

    @DeleteMapping("courier")
    ResponseEntity deleteCourier(@RequestParam Long courierId){
        courierService.deleteCourier(courierId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("order/{status}")
    ResponseEntity getOrdersByStatus(@PathVariable OrderStatus status){
        return ResponseEntity.ok().body(adminService.getOrdersByStatus(status));
    }

    @MessageMapping("admin/order/{orderId}")
    @SendTo({"/order/placed", "/order/assembling", "/order/assembled", "/order/delivering", "/order/{orderId}"})
    public OrderMessage setOrderStatus(Principal principal, @DestinationVariable Long orderId, OrderStatus status){
        try{
            return adminService.setOrderStatus(principal.getName(), orderId, status);
        } catch(NoSuchElementException ex){
            log.warn(ex.getMessage());
        }
        return new OrderMessage();
    }
}
