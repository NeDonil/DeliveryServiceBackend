package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.dto.request.ShortOrderDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.AddressDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.CustomerDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.messages.OrderMessage;
import com.vorstu.DeliveryServiceBackend.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/customer")
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @GetMapping
    public CustomerDTO getCustomerInfo(Principal principal){
        return customerService.getCustomerInfo(principal.getName());
    }

    @GetMapping("order")
    public List<OrderDTO> getAllOrders(Principal principal){
        return customerService.getCustomerOrders(principal.getName());
    }

    @GetMapping("order/current")
    public OrderDTO getCurrentOrder(Principal principal){
        return customerService.getCurrentOrder(principal.getName());
    }

    @GetMapping("order/{orderId}")
    public OrderDTO getOrder(Principal principal, @PathVariable Long orderId){
        return customerService.getOrder(principal.getName(), orderId);
    }

    @PutMapping("order/current")
    public OrderDTO updateCurrentOrder(Principal principal,
                                       @RequestBody
                                       @Valid ShortOrderDTO order){
        return customerService.updateCurrentOrder(principal.getName(), order);
    }

    @MessageMapping("customer/order/{orderId}")
    @SendTo({"/order/placed", "/order/{orderId}"})
    public OrderMessage makeOrder(Principal principal, @DestinationVariable Long orderId, OrderAction action){
        return customerService.doAction(principal.getName(), orderId, action);
    }

    @GetMapping("address")
    public List<AddressDTO> getAddresses(Principal principal){
        return customerService.getAddresses(principal.getName());
    }

    @PostMapping("address")
    public AddressDTO getAddresses(Principal principal, @RequestBody String address){
        return customerService.createAddress(principal.getName(), address);
    }
}
