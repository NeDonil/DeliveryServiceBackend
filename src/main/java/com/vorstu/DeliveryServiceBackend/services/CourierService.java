package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.CourierEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.CourierEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.CourierRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.dto.request.FullCourierDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.CourierDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.mappers.CourierListMapper;
import com.vorstu.DeliveryServiceBackend.mappers.CourierMapper;
import com.vorstu.DeliveryServiceBackend.mappers.OrderListMapper;
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
    OrderListMapper orderListMapper;
    @Autowired
    CourierListMapper courierListMapper;

    @Autowired
    CourierMapper courierMapper;

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

