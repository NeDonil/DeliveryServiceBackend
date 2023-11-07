package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.mappers.OrderListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    OrderListMapper orderListMapper;

    @Autowired
    OrderRepository orderRepository;
    public List<OrderDTO> getOrdersByStatus(OrderStatus status){
        return orderListMapper.toDTOList(orderRepository.findAllOrdersByStatus(status));
    }
}
