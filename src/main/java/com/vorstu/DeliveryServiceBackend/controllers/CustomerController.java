package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.dto.CustomerDTO;
import com.vorstu.DeliveryServiceBackend.db.dto.OrderDTO;
import com.vorstu.DeliveryServiceBackend.db.entities.CustomerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.repositories.CustomerRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    public ResponseEntity getCurrentOrder(Principal principal){
        CustomerEntity customerEntity = customerRepository.findUserByEmail(principal.getName());
        OrderEntity currentOrderEntity = orderRepository.findCurrentOrderByCustomerId(customerEntity.getId());
        OrderDTO currentOrderDTO = OrderDTO.fromEntity(currentOrderEntity);

        return ResponseEntity.ok().body(currentOrderDTO);
    }
}
