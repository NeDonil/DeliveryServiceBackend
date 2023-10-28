package com.vorstu.DeliveryServiceBackend.config;

import com.vorstu.DeliveryServiceBackend.db.entities.*;
import com.vorstu.DeliveryServiceBackend.db.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
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
    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Override
    public void run(String... args){
        AddressEntity address1 = new AddressEntity("Kukolkina 11 p4 kv 10");
        AddressEntity address2 = new AddressEntity("Karla Marksa 67/2  p4 kv 4101");
        AddressEntity address3 = new AddressEntity("40 let Octyabrya 12 p1 kv 2");

        UserCredentialsEntity crdntls1 = new UserCredentialsEntity("dima@mail.ru", "12345");
        UserCredentialsEntity crdntls2 = new UserCredentialsEntity("dima1@mail.ru", "54321");
        UserCredentialsEntity crdntls3 = new UserCredentialsEntity("dima2@mail.ru", "qwerrty");
        UserCredentialsEntity crdntls4 = new UserCredentialsEntity("dima_rudnev@gmail.ru", "difficultpswd");
        userCredentialsRepository.saveAll(Arrays.asList(crdntls1, crdntls2, crdntls3, crdntls4));

        AdminEntity admin1 = new AdminEntity("Rudneva Olesya");
        admin1.setCredentials(crdntls1);

        AdminEntity admin2 = new AdminEntity("Shmatko Galyna");
        admin2.setCredentials(crdntls2);

        AdminEntity admin3 = new AdminEntity("Gallagher Fiona");
        admin3.setCredentials(crdntls3);

        PaymentDataEntity payment1 = new PaymentDataEntity(4000000L);
        PaymentDataEntity payment2 = new PaymentDataEntity(4060060L);
        PaymentDataEntity payment3 = new PaymentDataEntity(1600000000L);
        paymentDataRepository.saveAll(Arrays.asList(payment1, payment2, payment3));

        CustomerEntity customer1 = new CustomerEntity("Dmitry Rudnev");
        customer1.setAddresses(Arrays.asList(address1));
        customer1.setCredentials(crdntls4);
        customer1.setPaymentData(payment1);
        customerRepository.save(customer1);

        adminRepository.saveAll(Arrays.asList(admin1, admin2, admin3));
        addressRepository.saveAll(Arrays.asList(address1, address2, address3));

        ProductEntity product1 = new ProductEntity("Alpen Gold", "desk1", 1L, 100000L);
        ProductEntity product2 = new ProductEntity("Cucumber", "desk2", 200L, 150000L);
        ProductEntity product3 = new ProductEntity("Apple", "desk3", 23L, 30000L);
        ProductEntity product4 = new ProductEntity("Chicken Fillet", "desk4", 15L, 400000L);
        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4));

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
