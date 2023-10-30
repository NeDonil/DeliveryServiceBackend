package com.vorstu.DeliveryServiceBackend.config;

import com.vorstu.DeliveryServiceBackend.db.entities.*;
import com.vorstu.DeliveryServiceBackend.db.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Initializer implements CommandLineRunner {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AssemblerRepository assemblerRepository;
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void run(String... args){
        AdminEntity admin1 = new AdminEntity("Rudneva Olesya", "lesya@mail.ru", "12345");
        AdminEntity admin2 = new AdminEntity("Shmatko Galyna", "whore@mail.ru", "superpswrd");
        AdminEntity admin3 = new AdminEntity("Gallagher Fiona", "fiona@mail.ru", "qwerrty");

        CustomerEntity customer1 = new CustomerEntity("Dmitry Rudnev", "dimas@gmail.com", "dima12345");
        customer1.getAddresses()
                .add(new AddressEntity("Kukolkina 11 p4 kv 10"));
        customer1.getAddresses()
                .add(new AddressEntity("Kukolkina 11 p5 kv 11"));
        customerRepository.save(customer1);

        CustomerEntity customer2 = new CustomerEntity("Danil Svinoukhov", "svinouhov03@gmail.com", "superhardpassword");
        customerRepository.save(customer2);

        adminRepository.saveAll(Arrays.asList(admin1, admin2, admin3));

        ProductEntity product1 = new ProductEntity("Alpen Gold", "desk1", 1L, 100000L);
        ProductEntity product2 = new ProductEntity("Cucumber", "desk2", 200L, 150000L);
        ProductEntity product3 = new ProductEntity("Apple", "desk3", 23L, 30000L);
        ProductEntity product4 = new ProductEntity("Chicken Fillet", "desk4", 15L, 400000L);

        GroupEntity g1 = new GroupEntity("General");
        g1.setProducts(Stream.of(product1, product2, product3, product4).collect(Collectors.toSet()));

        GroupEntity g2 = new GroupEntity("Meal");
        g2.setProducts(Stream.of(product4).collect(Collectors.toSet()));

        GroupEntity g3 = new GroupEntity("Vegetables");
        g3.setProducts(Stream.of(product2).collect(Collectors.toSet()));

        GroupEntity g4 = new GroupEntity("Fruits");
        g4.setProducts(Stream.of(product3).collect(Collectors.toSet()));

        groupRepository.saveAll(Arrays.asList(g1, g2, g3, g4));

        OrderItemEntity item1 = new OrderItemEntity(product1, 10L);
        OrderItemEntity item2 = new OrderItemEntity(product2, 17L);
        OrderItemEntity item3 = new OrderItemEntity(product3, 1L);
        OrderItemEntity item4 = new OrderItemEntity(product3, 100L);

        OrderEntity order1 = new OrderEntity(customer1);
        order1.getItems().add(item1);

        OrderEntity order2 = new OrderEntity(customer1);
        order2.getItems().add(item2);
        order2.getItems().add(item3);
        order2.setStatus(OrderStatus.PLACED);

        OrderEntity order3 = new OrderEntity(customer1);
        order3.getItems().add(item4);
        order3.setStatus(OrderStatus.DELIVERED);

        OrderEntity order4 = new OrderEntity(customer2);

        orderRepository.saveAll(Arrays.asList(order1, order2, order3, order4));

        courierRepository.save(new CourierEntity("Danil Wantropp", "wanropp@email.com", "w12345"));
        assemblerRepository.save(new AssemblerEntity("Marina Kovaleva", "marina@email.com", "loqiemean12345"));
    }
}
