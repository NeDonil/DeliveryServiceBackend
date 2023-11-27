package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.CustomerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderItemEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.db.repositories.AddressRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.CustomerRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.ProductRepository;
import com.vorstu.DeliveryServiceBackend.dto.request.ShortOrderDTO;
import com.vorstu.DeliveryServiceBackend.dto.request.ShortOrderItemDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.AddressDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.CustomerDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.mappers.AddressListMapper;
import com.vorstu.DeliveryServiceBackend.mappers.CustomerMapper;
import com.vorstu.DeliveryServiceBackend.mappers.OrderListMapper;
import com.vorstu.DeliveryServiceBackend.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderListMapper orderListMapper;

    @Autowired
    AddressListMapper addressListMapper;

    public CustomerDTO getCustomerInfo(String email){
        CustomerEntity customer = customerRepository.findUserByEmail(email);
        return customerMapper.toDTO(customer);
    }

    public List<OrderDTO> getCustomerOrders(String email){
        CustomerEntity customerEntity = customerRepository.findUserByEmail(email);
        List<OrderEntity> orderEntityList = orderRepository.findAllOrdersByCustomerId(customerEntity.getId());
        return orderListMapper.toDTOList(orderEntityList);
    }

    public OrderDTO getCurrentOrder(String email){
        CustomerEntity customerEntity = customerRepository.findUserByEmail(email);
        OrderEntity currentOrderEntity = orderRepository.findCurrentOrderByCustomerId(customerEntity.getId());
        return orderMapper.toDTO(currentOrderEntity);
    }

    public OrderDTO getOrder(String email, Long orderId) throws NoSuchElementException{
        CustomerEntity customerEntity = customerRepository.findUserByEmail(email);
        Optional<OrderEntity> orderEntityCandid = orderRepository.findById(orderId);

        if(!orderEntityCandid.isPresent()){
            throw new NoSuchElementException(String.format("Order with id %d is not found", orderId));
        }

        //Todo: check customer before
        return orderMapper.toDTO(orderEntityCandid.get());
    }

    public OrderDTO updateCurrentOrder(String email, ShortOrderDTO order){
        CustomerEntity customerEntity = customerRepository.findUserByEmail(email);
        OrderEntity orderEntity = orderRepository.findCurrentOrderByCustomerId(customerEntity.getId());

        List<OrderItemEntity> orderItemEntities = orderEntity.getItems();
        Iterator<OrderItemEntity> orderItemEntitiesIterator = orderItemEntities.iterator();

        if(order.getComment() != null){
            orderEntity.setComment(order.getComment());
        }

        if(order.getAddress() != null){
            Long addressId = order.getAddress().getId();
            orderEntity.setAddress(addressRepository.findById(addressId).get());
        }

        while(orderItemEntitiesIterator.hasNext()){
            OrderItemEntity entity = orderItemEntitiesIterator.next();
            Optional<ShortOrderItemDTO> itemCandid = order.getItems()
                    .stream()
                    .filter(x -> x.getId() == entity.getId())
                    .findFirst();

            if(itemCandid.isPresent()){
                entity.setCount(itemCandid.get().getCount());
                order.getItems().remove(itemCandid.get());
            } else {
                orderItemEntitiesIterator.remove();
            }
        }

        for(ShortOrderItemDTO newItem : order.getItems()){
            OrderItemEntity newOrderItemEntity = new OrderItemEntity();

            Long productId = newItem.getProduct().getId();
            newOrderItemEntity.setProduct(productRepository.findById(productId).get());

            newOrderItemEntity.setCount(newItem.getCount());

            orderItemEntities.add(newOrderItemEntity);
        }

        orderEntity.setItems(orderItemEntities);
        return orderMapper.toDTO(orderRepository.save(orderEntity));
    }

    public void doAction(String email, Long orderId, OrderAction action) throws NoSuchElementException{
        CustomerEntity customer = customerRepository.findUserByEmail(email);
        Optional<OrderEntity> orderEntityCandid = orderRepository.findById(orderId);
        if(!orderEntityCandid.isPresent()){
            throw new NoSuchElementException(String.format("Order with id %d not found", orderId));
        }

        OrderEntity orderEntity = orderEntityCandid.get();
        if(orderEntity.getCustomer() == customer){
            switch (action) {
                case MAKE -> {
                    orderEntity.setStatus(OrderStatus.PLACED);
                    orderEntity.setBeginDate(LocalDateTime.now());
                    orderRepository.save(orderEntity);

                    OrderEntity newOrderEntity = new OrderEntity(customer);
                    orderRepository.save(newOrderEntity);
                }
                case REFUSE -> {
                    orderEntity.setStatus(OrderStatus.REJECTED);
                    orderEntity.setEndDate(LocalDateTime.now());
                    orderRepository.save(orderEntity);
                }
            }
        }
    }

    public List<AddressDTO> getAddresses(String email){
        return addressListMapper.toDTOList(
                addressRepository.findAllAddressesByEmail(email)
        );
    }
}
