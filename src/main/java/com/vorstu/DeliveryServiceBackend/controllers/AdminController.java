package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.CourierEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.AssemblerRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.CourierRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.dto.request.FullAssemblerDTO;
import com.vorstu.DeliveryServiceBackend.dto.request.FullCourierDTO;
import com.vorstu.DeliveryServiceBackend.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    AssemblerRepository assemblerRepository;

    @Autowired
    CourierRepository courierRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CourierListMapper courierListMapper;

    @Autowired
    CourierMapper courierMapper;

    @Autowired
    AssemblerListMapper assemblerListMapper;

    @Autowired
    AssemblerMapper assemblerMapper;

    @Autowired
    OrderListMapper orderListMapper;

    @GetMapping("assembler")
    ResponseEntity getAssemblers(){
        List<AssemblerEntity> assemblerEntities = new ArrayList();
        assemblerRepository.findAll().forEach(assemblerEntities::add);
        return ResponseEntity.ok().body(assemblerListMapper.toDTOList(assemblerEntities));
    }

    @PostMapping("assembler")
    ResponseEntity addAssembler(@RequestBody FullAssemblerDTO assembler){
        AssemblerEntity assemblerEntity = new AssemblerEntity(assembler.getFio(), assembler.getEmail(),
                assembler.getPassword());
        assemblerRepository.save(assemblerEntity);
        return ResponseEntity.ok().build();
    }

    @PutMapping("assembler")
    ResponseEntity updateAssembler(@RequestParam Long assemblerId,
                                @RequestBody FullAssemblerDTO assembler){
        AssemblerEntity assemblerEntity = assemblerRepository.findById(assemblerId).get();
        assemblerEntity.setFio(assembler.getFio());
        assemblerRepository.save(assemblerEntity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("assembler")
    ResponseEntity deleteAssembler(@RequestParam Long assemblerId){
        assemblerRepository.deleteById(assemblerId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("courier")
    ResponseEntity getCouriers(){
        List<CourierEntity> courierEntities = new ArrayList();
        courierRepository.findAll().forEach(courierEntities::add);
        return ResponseEntity.ok().body(courierListMapper.toDTOList(courierEntities));
    }

    @PostMapping("courier")
    ResponseEntity addCourier(@RequestBody FullCourierDTO courier){
        CourierEntity courierEntity = new CourierEntity(courier.getFio(), courier.getEmail(), courier.getPassword());
        courierRepository.save(courierEntity);
        return ResponseEntity.ok().build();
    }

    @PutMapping("courier")
    ResponseEntity updateCourier(@RequestParam Long courierId,
                                   @RequestBody FullCourierDTO courier){
        CourierEntity courierEntity = courierRepository.findById(courierId).get();
        courierEntity.setFio(courier.getFio());
        courierRepository.save(courierEntity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("courier")
    ResponseEntity deleteCourier(@RequestParam Long courierId){
        courierRepository.deleteById(courierId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("order/{status}")
    ResponseEntity getOrdersByStatus(@PathVariable OrderStatus status){
        return ResponseEntity.ok().body(
                orderListMapper.toDTOList(orderRepository.findAllOrdersByStatus(status))
        );
    }
}
