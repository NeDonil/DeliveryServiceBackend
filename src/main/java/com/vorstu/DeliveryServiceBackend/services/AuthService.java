package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.db.entities.CustomerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.repositories.CustomerRepository;
import com.vorstu.DeliveryServiceBackend.db.repositories.OrderRepository;
import com.vorstu.DeliveryServiceBackend.dto.request.NewUserDTO;
import com.vorstu.DeliveryServiceBackend.exception.DuplicateEmailException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @SneakyThrows
    public void register(NewUserDTO user){
        CustomerEntity newUser = new CustomerEntity(user.getFio(), user.getEmail(), user.getPassword());
        OrderEntity firstOrder = new OrderEntity(newUser);

        try {
            customerRepository.save(newUser);
            orderRepository.save(firstOrder);
        } catch(Exception ex){
            throw new DuplicateEmailException();
        }
    }
}
