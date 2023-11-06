package com.vorstu.DeliveryServiceBackend.controllers;

import com.vorstu.DeliveryServiceBackend.db.entities.CustomerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderItemEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.AddressRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.CustomerRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.ProductRepository;
import com.vorstu.DeliveryServiceBackend.dto.request.ShortOrderDTO;
import com.vorstu.DeliveryServiceBackend.dto.request.ShortOrderItemDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.CustomerDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.ProductDTO;
import com.vorstu.DeliveryServiceBackend.mappers.AddressMapper;
import com.vorstu.DeliveryServiceBackend.mappers.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerMapper customerMapper;
    @GetMapping
    public ResponseEntity getCustomerInfo(Principal principal){
        CustomerEntity customer = customerRepository.findUserByEmail(principal.getName());
        CustomerDTO customerDTO = customerMapper.toDTO(customer);
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
    public ResponseEntity updateCurrentOrder(Principal principal,
                                          @RequestBody ShortOrderDTO order){
        CustomerEntity customerEntity = customerRepository.findUserByEmail(principal.getName());
        OrderEntity orderEntity = orderRepository.findCurrentOrderByCustomerId(customerEntity.getId());

        List<OrderItemEntity> orderItemEntities = orderEntity.getItems();
        Iterator<OrderItemEntity> orderItemEntitiesIterator = orderItemEntities.iterator();

        if(order.getComment() != null){
            orderEntity.setComment(order.getComment());
        }

        if(order.getAddress() != null){
            Long addressId = order.getAddress().getId();
            orderEntity.setAddress(addressRepository.findById(addressId).get());
        }

        while(orderItemEntitiesIterator.hasNext()){
            OrderItemEntity entity = orderItemEntitiesIterator.next();
            Optional<ShortOrderItemDTO> itemCandid = order.getItems()
                    .stream()
                    .filter(x -> x.getId() == entity.getId())
                    .findFirst();

            if(itemCandid.isPresent()){
                entity.setCount(itemCandid.get().getCount());
                order.getItems().remove(itemCandid.get());
            } else {
                orderItemEntitiesIterator.remove();
            }
        }

        for(ShortOrderItemDTO newItem : order.getItems()){
            OrderItemEntity newOrderItemEntity = new OrderItemEntity();

            Long productId = newItem.getProduct().getId();
            newOrderItemEntity.setProduct(productRepository.findById(productId).get());

            newOrderItemEntity.setCount(newItem.getCount());

            orderItemEntities.add(newOrderItemEntity);
        }

        orderEntity.setItems(orderItemEntities);
        orderRepository.save(orderEntity);

        return ResponseEntity.ok().build();
    }

    @GetMapping("order/{orderId}/action/{action}")
    public ResponseEntity doActionOnOrder(Principal principal,
                                          @PathVariable Long orderId,
                                          @PathVariable OrderAction action){
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
