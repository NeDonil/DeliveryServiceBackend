package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.dto.request.FullAssemblerDTO;
import com.vorstu.DeliveryServiceBackend.dto.request.FullCourierDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.AssemblerDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.CourierDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderWithEmployeeDTO;
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

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
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
    List<AssemblerDTO> getAssemblers(){
        return assemblerService.getAssemblers();
    }

    @PostMapping("assembler")
    AssemblerDTO addAssembler(@RequestBody
                              @Valid FullAssemblerDTO assembler){
        return assemblerService.createAssembler(assembler);
    }

    @PutMapping("assembler")
    AssemblerDTO updateAssembler(@RequestParam Long assemblerId,
                                 @RequestBody
                                 @Valid FullAssemblerDTO assembler){
        return assemblerService.updateAssembler(assemblerId, assembler);
    }

    @DeleteMapping("assembler")
    void deleteAssembler(@RequestParam Long assemblerId){
        assemblerService.deleteAssembler(assemblerId);
    }

    @GetMapping("courier")
    List<CourierDTO> getCouriers(){
        return courierService.getCouriers();
    }

    @PostMapping("courier")
    CourierDTO addCourier(@RequestBody
                          @Valid FullCourierDTO courier){
        return courierService.createCourier(courier);
    }

    @PutMapping("courier")
    CourierDTO updateCourier(@RequestParam Long courierId,
                             @RequestBody
                             @Valid FullCourierDTO courier){
        return courierService.updateCourier(courierId, courier);
    }

    @DeleteMapping("courier")
    ResponseEntity deleteCourier(@RequestParam Long courierId){
        courierService.deleteCourier(courierId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("order/{status}")
    List<OrderWithEmployeeDTO> getOrdersByStatus(@PathVariable OrderStatus status){
        return adminService.getOrdersByStatus(status);
    }

    @MessageMapping("admin/order/{orderId}")
    @SendTo({"/order/placed", "/order/assembling", "/order/assembled", "/order/delivering", "/order/{orderId}"})
    public OrderMessage setOrderStatus(Principal principal, @DestinationVariable Long orderId, OrderStatus status){
        return adminService.setOrderStatus(principal.getName(), orderId, status);
    }
}
