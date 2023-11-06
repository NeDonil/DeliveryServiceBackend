package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.AssemblerRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.mappers.OrderListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AssemblerService {
    @Autowired
    AssemblerRepository assemblerRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderListMapper orderListMapper;

    public List<OrderDTO> getOrders(){
        List<OrderEntity> orderEntities = orderRepository.findAllOrdersByStatus(OrderStatus.PLACED);
        return orderListMapper.toDTOList(orderEntities);
    }

    public void doActionOnOrder(String email, Long orderId, OrderAction action) throws NoSuchElementException {
        AssemblerEntity assembler = assemblerRepository.findUserByEmail(email);
        Optional<OrderEntity> orderEntityCandid = orderRepository.findById(orderId);

        if(!orderEntityCandid.isPresent()){
            throw new NoSuchElementException(String.format("Order with id %d not found", orderId));
        }

        OrderEntity orderEntity = orderEntityCandid.get();
        switch (action) {
            case TO_ASSEMBLY -> {
                orderEntity.setStatus(OrderStatus.ASSEMBLING);
                orderEntity.setAssembler(assembler);
            }
            case TO_ASSEMBLED -> {
                orderEntity.setStatus(OrderStatus.ASSEMBLED);
            }
            case REFUSE -> {
                orderEntity.setStatus(OrderStatus.PLACED);
                orderEntity.setAssembler(null);
            }
        }

        orderRepository.save(orderEntity);
    }
}
