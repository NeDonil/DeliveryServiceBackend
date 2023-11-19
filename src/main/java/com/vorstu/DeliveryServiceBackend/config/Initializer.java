package com.vorstu.DeliveryServiceBackend.config;

import com.vorstu.DeliveryServiceBackend.db.entities.*;
import com.vorstu.DeliveryServiceBackend.db.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        ProductEntity p1 = new ProductEntity("Чизкейк", "Описание чизкейка",
                "https://cm.samokat.ru/processed/m/original/95fd4465-27a6-11ed-885a-08c0eb32014b_1636298318.jpg",
                100L, 20L);

        ProductEntity p2 = new ProductEntity("Куриное филе", "Описание куриного филе",
                "https://cm.samokat.ru/processed/m/public/9676203a013c263d_4670117690775-1.jpg",
                100L, 400L);

        ProductEntity p3 = new ProductEntity("Хлеб", "Описание хлеба",
                "https://cm.samokat.ru/processed/m/original/7323ffdd-3fff-11ed-b96c-08c0eb32008b_1669197198.jpg",
                100L, 80L);

        ProductEntity p4 = new ProductEntity("Вода", "Описание воды",
                "https://cm.samokat.ru/processed/m/original/8621b85b-8808-11ec-ae6d-08c0eb320147_1952036370.jpg",
                100L, 50L);

        ProductEntity p5 = new ProductEntity("Апельсины", "Описание апельсинов",
                "https://cm.samokat.ru/processed/m/original/69813_1517928215.jpg",
                100L, 150L);

        ProductEntity p6 = new ProductEntity("Чай черный Майский Отборный цейлонский, 25 пакетиков", "Описание чая",
                "https://cm.samokat.ru/processed/m/original/112562_643953137.jpg",
                100L, 120L);

        ProductEntity p7 = new ProductEntity("Сахар", "Описание Сахара",
                "https://cm.samokat.ru/processed/m/original/124584_1965506859.jpg",
                100L, 100L);

        GroupEntity g1 = new GroupEntity("Все товары", "https://cm.samokat.ru/processed/category/4ccedaf7-4296-42fe-9519-6f04e78704ab.jpg");
        g1.setProducts(Stream.of(p1, p2, p3, p4, p5, p6, p7).collect(Collectors.toSet()));

        GroupEntity g2 = new GroupEntity("Мясо", "https://cm.samokat.ru/processed/public/b6db88b1e3657695______________7.jpg");
        g2.setProducts(Stream.of(p2).collect(Collectors.toSet()));

        GroupEntity g3 = new GroupEntity("Овощи", "https://cm.samokat.ru/processed/category/1694187180-pic1.jpg");
        g3.setProducts(Stream.of(p7, p3).collect(Collectors.toSet()));

        GroupEntity g4 = new GroupEntity("Хлеб", "https://cm.samokat.ru/processed/original/160441_349334568.jpg");
        g4.setProducts(Stream.of(p1, p7).collect(Collectors.toSet()));

        GroupEntity g5 = new GroupEntity("Бакалея", "https://cm.samokat.ru/processed/original/153793_1354391565.jpg");
        GroupEntity g6 = new GroupEntity("Чипсы и сухарики", "https://cm.samokat.ru/processed/public/fd4a6ecb80cdacae_pepsico_3.jpg");
        GroupEntity g7 = new GroupEntity("Вода", "https://cm.samokat.ru/processed/original/88488_1136633278.jpg");
        GroupEntity g8 = new GroupEntity("Морозилка", "https://cm.samokat.ru/processed/original/85886_1742409590.jpg");
        GroupEntity g9 = new GroupEntity("Молоко, яйца, хлеб", "https://cm.samokat.ru/processed/public/9ecb524e05b2b4b3_________________________.jpg");

        groupRepository.saveAll(Arrays.asList(g1, g2, g3, g5, g6, g7, g8, g9));

        OrderItemEntity item1 = new OrderItemEntity(p1, 10L);
        OrderItemEntity item2 = new OrderItemEntity(p2, 17L);
        OrderItemEntity item3 = new OrderItemEntity(p3, 1L);
        OrderItemEntity item4 = new OrderItemEntity(p3, 100L);

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
