package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.CourierEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.CourierRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.mappers.OrderListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CourierService {
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderListMapper orderListMapper;

    public List<OrderDTO> getOrders(){
        List<OrderEntity> orderEntities = orderRepository.findAllOrdersByStatus(OrderStatus.ASSEMBLED);
        return orderListMapper.toDTOList(orderEntities);
    }

    public void doActionOnOrder(String email, Long orderId, OrderAction action) throws NoSuchElementException {
        CourierEntity courier = courierRepository.findUserByEmail(email);
        Optional<OrderEntity> orderEntityCandid = orderRepository.findById(orderId);

        if(!orderEntityCandid.isPresent()){
            throw new NoSuchElementException(String.format("Order with id %d not found", orderId));
        }

        OrderEntity orderEntity = orderEntityCandid.get();
        switch (action) {
            case TO_DELIVERY -> {
                orderEntity.setStatus(OrderStatus.DELIVERING);
                orderEntity.setCourier(courier);
            }
            case TO_DELIVERED -> {
                orderEntity.setStatus(OrderStatus.DELIVERED);
                orderEntity.setEndDate(LocalDateTime.now());
            }
            case REFUSE -> {
                orderEntity.setStatus(OrderStatus.ASSEMBLED);
                orderEntity.setCourier(null);
            }
        }

        orderRepository.save(orderEntity);
    }
}

