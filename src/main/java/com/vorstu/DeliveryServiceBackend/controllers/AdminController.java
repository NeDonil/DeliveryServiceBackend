package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.dto.request.FullAssemblerDTO;
import com.vorstu.DeliveryServiceBackend.dto.request.FullCourierDTO;
import com.vorstu.DeliveryServiceBackend.services.AdminService;
import com.vorstu.DeliveryServiceBackend.services.AssemblerService;
import com.vorstu.DeliveryServiceBackend.services.CourierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
