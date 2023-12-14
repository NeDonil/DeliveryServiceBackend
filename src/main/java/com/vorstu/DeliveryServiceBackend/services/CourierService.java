package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.*;
import com.vorstu.DeliveryServiceBackend.db.entities.CourierEntity;
import com.vorstu.DeliveryServiceBackend.db.repositories.CourierRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.dto.request.FullCourierDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.CourierDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderWithStatusDTO;
import com.vorstu.DeliveryServiceBackend.exception.EmployeeNotFoundException;
import com.vorstu.DeliveryServiceBackend.exception.IllegalOrderOperationException;
import com.vorstu.DeliveryServiceBackend.exception.OrderNotFoundException;
import com.vorstu.DeliveryServiceBackend.mappers.*;
import com.vorstu.DeliveryServiceBackend.messages.OrderMessage;
import com.vorstu.DeliveryServiceBackend.services.action.resolver.ActionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CourierService {
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderListMapper orderListMapper;
    @Autowired
    CourierListMapper courierListMapper;

    @Autowired
    CourierMapper courierMapper;

    @Autowired
    BaseUserMapper baseUserMapper;

    @Autowired
    ActionResolver<CourierEntity> courierActionResolver;

    public List<OrderDTO> getOrders(){
        List<OrderEntity> orderEntities = orderRepository.findAllOrdersByStatus(OrderStatus.ASSEMBLED);
        return orderListMapper.toDTOList(orderEntities);
    }

    public OrderWithStatusDTO getCurrentOrder(String email) {
        CourierEntity courier = courierRepository.findUserByEmail(email);
        OrderEntity order = orderRepository.findCurrentEmployeeOrder(courier.getId(), Arrays.asList(OrderStatus.DELIVERING))
                .orElse(new OrderEntity());
        return new OrderWithStatusDTO(order.getStatus(), orderMapper.toDTO(order));
    }

    public OrderMessage doAction(String email, Long orderId, OrderAction action)  {
        CourierEntity courier = courierRepository.findUserByEmail(email);
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order not found, id = ", orderId)));

        CourierEntity orderCourier = orderEntity.getCourier();
        if(orderCourier != null && orderCourier.getId() != courier.getId()){
            throw new IllegalOrderOperationException(String.format("You cannot operate with order(%d)", orderId));
        }

        courierActionResolver.resolve(action, orderEntity, courier);
        orderRepository.save(orderEntity);

        return new OrderMessage(action, baseUserMapper.toDTO(courier), orderMapper.toDTO(orderEntity));
    }

    public List<CourierDTO> getCouriers(){
        List<CourierEntity> courierEntities = new ArrayList();
        courierRepository.findAll().forEach(courierEntities::add);
        return courierListMapper.toDTOList(courierEntities);
    }

    public CourierDTO createCourier(FullCourierDTO courier){
        CourierEntity courierEntity = new CourierEntity(courier);
        return courierMapper.toDTO(courierRepository.save(courierEntity));
    }

    public CourierDTO updateCourier(Long courierId, FullCourierDTO courier) throws NoSuchElementException{
        CourierEntity courierEntity = courierRepository.findById(courierId)
                .orElseThrow( () -> new EmployeeNotFoundException(String.format("Courier not found, id = %d", courierId)));

        courierEntity.setFio(courier.getFio());
        return courierMapper.toDTO(courierRepository.save(courierEntity));
    }

    public void deleteCourier(Long courierId){
        courierRepository.deleteById(courierId);
    }
}

