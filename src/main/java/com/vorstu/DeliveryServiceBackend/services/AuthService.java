package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.db.entities.CustomerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.repositories.CustomerRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.dto.request.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    public void register(NewUserDTO user){
        CustomerEntity newUser = new CustomerEntity(user.getFio(), user.getEmail(), user.getPassword());
        OrderEntity firstOrder = new OrderEntity(newUser);

        customerRepository.save(newUser);
        orderRepository.save(firstOrder);
    }
}
