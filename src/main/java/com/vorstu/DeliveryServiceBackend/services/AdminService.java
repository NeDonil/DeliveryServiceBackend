package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.AdminEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.AdminRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderWithEmployeeDTO;
import com.vorstu.DeliveryServiceBackend.mappers.BaseUserMapper;
import com.vorstu.DeliveryServiceBackend.mappers.OrderListMapper;
import com.vorstu.DeliveryServiceBackend.mappers.OrderMapper;
import com.vorstu.DeliveryServiceBackend.messages.OrderMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminService {
    final OrderMapper orderMapper;

    final BaseUserMapper baseUserMapper;

    final OrderRepository orderRepository;

    final AdminRepository adminRepository;
    public List<OrderWithEmployeeDTO> getOrdersByStatus(OrderStatus status){
        List<OrderEntity> orders = orderRepository.findAllOrdersByStatus(status);
        return orders.stream()
                .map( order -> new OrderWithEmployeeDTO(
                        baseUserMapper.toDTO( order.getCourier() != null ? order.getCourier() :order.getAssembler()),
                        orderMapper.toDTO(order)))
                .collect(Collectors.toList());

    }

    public OrderMessage setOrderStatus(String email, Long orderId, OrderStatus status){
        AdminEntity admin = adminRepository.findAdminByEmail(email);
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);
        order.setStatus(status);
        orderRepository.save(order);
        return new OrderMessage(OrderActionMapper.getAction(status), baseUserMapper.toDTO(admin), orderMapper.toDTO(order));

    }
}
