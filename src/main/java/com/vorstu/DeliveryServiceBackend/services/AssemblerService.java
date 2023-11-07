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
import com.vorstu.DeliveryServiceBackend.mappers.AssemblerListMapper;
import com.vorstu.DeliveryServiceBackend.mappers.AssemblerMapper;
import com.vorstu.DeliveryServiceBackend.mappers.OrderListMapper;
import com.vorstu.DeliveryServiceBackend.services.action.resolver.ActionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AssemblerService {
    @Autowired
    AssemblerRepository assemblerRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderListMapper orderListMapper;

    @Autowired
    AssemblerListMapper assemblerListMapper;

    @Autowired
    AssemblerMapper assemblerMapper;

    @Autowired
    ActionResolver<AssemblerEntity> assemblerActionResolver;

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

        assemblerActionResolver.resolve(action, orderEntity, assembler);
        orderRepository.save(orderEntity);
    }

    public List<AssemblerDTO> getAssemblers(){
        List<AssemblerEntity> assemblerEntities = new ArrayList();
        assemblerRepository.findAll().forEach(assemblerEntities::add);
        return assemblerListMapper.toDTOList(assemblerEntities);
    }

    public AssemblerDTO createAssembler(FullAssemblerDTO assembler){
        AssemblerEntity assemblerEntity = new AssemblerEntity(
                assembler.getFio(),
                assembler.getEmail(),
                assembler.getPassword()
        );
        return assemblerMapper.toDTO(assemblerRepository.save(assemblerEntity));
    }

    public AssemblerDTO updateAssembler(Long assemblerId, FullAssemblerDTO assembler) throws NoSuchElementException{
        Optional<AssemblerEntity> assemblerEntityCandid = assemblerRepository.findById(assemblerId);
        if(!assemblerEntityCandid.isPresent()){
            throw new NoSuchElementException(String.format("Assembler with id %d not found", assemblerId));
        }

        AssemblerEntity assemblerEntity = assemblerEntityCandid.get();
        assemblerEntity.setFio(assembler.getFio());
        return assemblerMapper.toDTO(assemblerRepository.save(assemblerEntity));
    }

    public void deleteAssembler(Long assemblerId){
        assemblerRepository.deleteById(assemblerId);
    }
}
