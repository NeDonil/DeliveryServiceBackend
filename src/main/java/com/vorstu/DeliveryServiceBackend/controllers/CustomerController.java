package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.dto.CustomerDTO;
import com.vorstu.DeliveryServiceBackend.db.dto.OrderDTO;
import com.vorstu.DeliveryServiceBackend.db.entities.CustomerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.CustomerRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;
    @GetMapping
    public ResponseEntity getCustomerInfo(Principal principal){
        CustomerEntity customer = customerRepository.findUserByEmail(principal.getName());
        CustomerDTO customerDTO = CustomerDTO.fromEntity(customer);
        return ResponseEntity.ok().body(customerDTO);
    }

    @GetMapping("order")
    public ResponseEntity getAllOrders(Principal principal){
        CustomerEntity customerEntity = customerRepository.findUserByEmail(principal.getName());
        List<OrderDTO> orders = orderRepository.findAllOrdersByCustomerId(customerEntity.getId())
                .stream()
                .map(OrderDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("order/current")
    public ResponseEntity getCurrentOrder(Principal principal){
        CustomerEntity customerEntity = customerRepository.findUserByEmail(principal.getName());
        OrderEntity currentOrderEntity = orderRepository.findCurrentOrderByCustomerId(customerEntity.getId());
        OrderDTO currentOrderDTO = OrderDTO.fromEntity(currentOrderEntity);

        return ResponseEntity.ok().body(currentOrderDTO);
    }

    @GetMapping("order/{orderId}")
    public ResponseEntity getCurrentOrder(Principal principal,
                                          @PathVariable Long orderId){
        CustomerEntity customerEntity = customerRepository.findUserByEmail(principal.getName());
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        if(orderEntity.getCustomer() == customerEntity){
            return ResponseEntity.ok().body(OrderDTO.fromEntity(orderEntity));
        } else {
            return ResponseEntity.badRequest().body("Is not your order");
        }


    }

    @PutMapping("order/current")
    public ResponseEntity getCurrentOrder(Principal principal,
                                          @RequestBody OrderDTO order){
        /*CustomerEntity customerEntity = customerRepository.findUserByEmail(principal.getName());
        OrderEntity orderEntity = orderRepository.findCurrentOrderByCustomerId(customerEntity.getId());
        List<OrderItemEntity> orderItemEntities = orderEntity.getItems();
        orderItemEntities.clear();
        for(OrderItemDTO orderItem : order.getItems()){
            orderItemEntities.add(new orderItem.getShortProduct().getId())
        } ToDo: implementation without billon db queries*/
        return ResponseEntity.ok().build();
    }

    @GetMapping("order/{orderId}/action/{action}")
    public ResponseEntity doActionOnOrder(Principal principal,
                                          @PathVariable Long orderId,
                                          @PathVariable OrderActions action){
        CustomerEntity customer = customerRepository.findUserByEmail(principal.getName());
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        if(orderEntity.getCustomer() == customer){
            switch (action) {
                case MAKE -> {
                    orderEntity.setStatus(OrderStatus.PLACED);
                    orderEntity.setBeginDate(LocalDateTime.now());
                    OrderEntity newOrderEntity = new OrderEntity(customer);
                    orderRepository.save(newOrderEntity);
                }
                case REFUSE -> {
                    orderEntity.setStatus(OrderStatus.REJECTED);
                    orderEntity.setEndDate(LocalDateTime.now());
                    orderRepository.save(orderEntity);
                }
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
