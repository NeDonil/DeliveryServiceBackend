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
    AddressRepository addressRepository;
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
    @Autowired
    PaymentDataRepository paymentDataRepository;
    @Autowired
    ProductRepository productRepository;

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

        OrderItemEntity item1 = new OrderItemEntity(10L);
        item1.setProduct(product1);

        OrderItemEntity item2 = new OrderItemEntity(17L);
        item2.setProduct(product2);

        OrderItemEntity item3 = new OrderItemEntity(1L);
        item3.setProduct(product3);

        OrderEntity order1 = new OrderEntity("Give me that", LocalDateTime.now(), OrderStatus.MAKING, customer1);
        orderRepository.save(order1);
    }
}
