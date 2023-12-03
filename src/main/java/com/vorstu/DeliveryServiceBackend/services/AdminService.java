package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderWithEmployeeDTO;
import com.vorstu.DeliveryServiceBackend.mappers.BaseUserMapper;
import com.vorstu.DeliveryServiceBackend.mappers.OrderListMapper;
import com.vorstu.DeliveryServiceBackend.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    BaseUserMapper baseUserMapper;

    @Autowired
    OrderRepository orderRepository;
    public List<OrderWithEmployeeDTO> getOrdersByStatus(OrderStatus status){
        List<OrderEntity> orders = orderRepository.findAllOrdersByStatus(status);
        return orders.stream()
                .map( order -> new OrderWithEmployeeDTO(
                        baseUserMapper.toDTO( order.getCourier() != null ? order.getCourier() :order.getAssembler()),
                        orderMapper.toDTO(order)))
                .collect(Collectors.toList());

    }
}
