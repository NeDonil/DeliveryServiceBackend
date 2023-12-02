package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.*;
import com.vorstu.DeliveryServiceBackend.db.entities.CourierEntity;
import com.vorstu.DeliveryServiceBackend.db.repositories.CourierRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.dto.request.FullCourierDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.CourierDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.mappers.*;
import com.vorstu.DeliveryServiceBackend.messages.OrderMessage;
import com.vorstu.DeliveryServiceBackend.services.action.resolver.ActionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public OrderMessage doAction(String email, Long orderId, OrderAction action) throws NoSuchElementException {
        CourierEntity courier = courierRepository.findUserByEmail(email);
        Optional<OrderEntity> orderEntityCandid = orderRepository.findById(orderId);

        if(orderEntityCandid.isEmpty()){
            throw new NoSuchElementException(String.format("Order with id %d not found", orderId));
        }

        OrderEntity orderEntity = orderEntityCandid.get();

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
        CourierEntity courierEntity = new CourierEntity(
                courier.getFio(),
                courier.getEmail(),
                courier.getPassword()
        );
        return courierMapper.toDTO(courierRepository.save(courierEntity));
    }

    public CourierDTO updateCourier(Long courierId, FullCourierDTO courier) throws NoSuchElementException{
        Optional<CourierEntity> courierEntityCandid = courierRepository.findById(courierId);
        if(!courierEntityCandid.isPresent()){
            throw new NoSuchElementException(String.format("Courier with id %d not found", courierId));
        }

        CourierEntity courierEntity = courierEntityCandid.get();
        courierEntity.setFio(courier.getFio());
        return courierMapper.toDTO(courierRepository.save(courierEntity));
    }

    public void deleteCourier(Long courierId){
        courierRepository.deleteById(courierId);
    }
}

