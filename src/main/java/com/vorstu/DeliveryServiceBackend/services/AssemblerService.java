package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.AssemblerRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.dto.request.FullAssemblerDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.AssemblerDTO;
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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AssemblerService {
    @Autowired
    AssemblerRepository assemblerRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderListMapper orderListMapper;

    @Autowired
    AssemblerListMapper assemblerListMapper;

    @Autowired
    AssemblerMapper assemblerMapper;
    @Autowired
    BaseUserMapper baseUserMapper;


    @Autowired
    ActionResolver<AssemblerEntity> assemblerActionResolver;

    public List<OrderDTO> getOrders(){
        List<OrderEntity> orderEntities = orderRepository.findAllOrdersByStatus(OrderStatus.PLACED);
        return orderListMapper.toDTOList(orderEntities);
    }

    public OrderMessage doAction(String email, Long orderId, OrderAction action) {
        AssemblerEntity assembler = assemblerRepository.findUserByEmail(email);
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException(String.format("Order with id %d not found", orderId)));

        AssemblerEntity orderAssembler = orderEntity.getAssembler();
        if(orderAssembler != null && orderAssembler.getId() != assembler.getId()){
            throw new IllegalOrderOperationException(String.format("You cannot operate with order(%d)", orderId));
        }

        assemblerActionResolver.resolve(action, orderEntity, assembler);
        orderRepository.save(orderEntity);

        return new OrderMessage(action, baseUserMapper.toDTO(assembler), orderMapper.toDTO(orderEntity));
    }

    public OrderWithStatusDTO getCurrentOrder(String email) {
        AssemblerEntity assembler = assemblerRepository.findUserByEmail(email);
        OrderEntity order = orderRepository.findCurrentEmployeeOrder(assembler.getId(), List.of(OrderStatus.ASSEMBLING))
                .orElse(new OrderEntity());

        return new OrderWithStatusDTO(order.getStatus(), orderMapper.toDTO(order));
    }

    public List<AssemblerDTO> getAssemblers(){
        return assemblerListMapper.toDTOList(
                StreamSupport
                        .stream(assemblerRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList())
        );
    }

    public AssemblerDTO createAssembler(FullAssemblerDTO fullAssemblerDTO){
        AssemblerEntity assemblerEntity = new AssemblerEntity(fullAssemblerDTO);
        return assemblerMapper.toDTO(assemblerRepository.save(assemblerEntity));
    }

    public AssemblerDTO updateAssembler(Long assemblerId, FullAssemblerDTO assembler){
        AssemblerEntity assemblerEntity = assemblerRepository.findById(assemblerId)
                .orElseThrow(()-> new EmployeeNotFoundException(String.format("Assembler not found, id = {%d}", assemblerId)));
        assemblerEntity.setFio(assembler.getFio());

        return assemblerMapper.toDTO(assemblerRepository.save(assemblerEntity));
    }

    public void deleteAssembler(Long assemblerId){
        assemblerRepository.deleteById(assemblerId);
    }
}
