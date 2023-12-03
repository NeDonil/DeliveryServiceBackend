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
        AdminEntity admin1 = new AdminEntity("Фиона Галагер", "fiona@mail.ru", "f12345");

        CustomerEntity customer1 = new CustomerEntity("Дмитрий Руднев", "dimas@gmail.com", "dima12345");
        customer1.getAddresses()
                .add(new AddressEntity("Куколкина 11 п4 э4 кв 290"));
        customer1.getAddresses()
                .add(new AddressEntity("Беговая 6/3 п4 э5 кв 60"));
        customerRepository.save(customer1);

        CustomerEntity customer2 = new CustomerEntity("Данил Свиноухов", "svinoukhov03@gmail.com", "superhardpassword");
        customer2.getAddresses()
                .add(new AddressEntity("Беговая 6/3 п4 э5 кв 60"));
        customerRepository.save(customer2);

        adminRepository.saveAll(Arrays.asList(admin1));

        ProductEntity p1 = new ProductEntity("Морковь по-корейски Fresh Secret", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/3d4214cbe7e26439_4610092032047-1.jpg",
                100L, 176L);

        ProductEntity p2 = new ProductEntity("Гречка Свели", "Описание продукта",
                "https://cm.samokat.ru/processed/m/product_card/40ddd4d5-1c36-45ba-a294-60fecfd5a268.jpg",
                100L, 107L);

        ProductEntity p3 = new ProductEntity("Блинчики по-домашнему", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/6675082fa29e9912_4610092031996-1.jpg",
                100L, 136L);

        ProductEntity p4 = new ProductEntity("Сырники Creative Kitchen", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/951e3384ee773e22_4650061637675-1.jpg",
                100L, 119L);

        ProductEntity p5 = new ProductEntity("Комплексный обед", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/f431fbf095a41451_4601445206680-1.jpg",
                100L, 315L);
        //
        ProductEntity p6 = new ProductEntity("Ржано-пшеничный хлеб ЭкоХлеб", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/a170360eebcf06e5_4607135168955-1.jpg",
                100L, 64L);

        ProductEntity p7 = new ProductEntity("Пшеничный хлеб ЭкоХлеб Монж", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/c8f1c79b2281cc5e_4607135168924-1.jpg",
                100L, 87L);

        ProductEntity p8 = new ProductEntity("Осетинский пирог Рамонти пайз", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/1ab7217b0f187c74_4631158748627-1.jpg",
                100L, 169L);

        ProductEntity p9 = new ProductEntity("Самса Mr. Food с говядиной", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/f3fbb4509de69b38_4630061958789-1.jpg",
                100L, 85L);

        ProductEntity p10 = new ProductEntity("Сосиска в тесте Mr. Food", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/be2eb4fa45f71b6c_4630061956341-1.jpg",
                100L, 86L);

        ProductEntity p11 = new ProductEntity("Слойка Лимак с вишневой начинкой", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/174846_1989637154.jpg",
                100L, 42L);
        ProductEntity p12 = new ProductEntity("Ромовая баба Хлебозавод №7", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/1d529a1460bfdaf1_4607100970354-1.jpg",
                100L, 41L);
        //

        ProductEntity p13 = new ProductEntity("Томаты Эко-культура коктейльные", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/8156f371aa0111f8_4635000821216-1.jpg",
                100L, 99L);

        ProductEntity p14 = new ProductEntity("Огурец Луховицкий длинноплодный", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/170757066_1561048227.jpg",
                100L, 69L);

        ProductEntity p15 = new ProductEntity("Перец желтый сочный классный", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/170753280_976093574.jpg",
                100L, 189L);

        ProductEntity p16 = new ProductEntity("Грибы шампиньоны, гриль", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/c3eb76f4-c465-11eb-85ac-1c34dae33151_699928974.jpg",
                100L, 239L);

        ProductEntity p17 = new ProductEntity("Картофель красный, в сетке", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/170753259_967847855.jpg",
                100L, 89L);
        //
        ProductEntity p18 = new ProductEntity("Филе грудки цыпленка-бройлера", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/948a127d-9f93-11ec-b967-08c0eb32008b_304784882.jpg",
                100L, 429L);

        ProductEntity p19 = new ProductEntity("Шницель Слово Мясника охлажденный", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/97285_1355385209.jpg",
                100L, 223L);

        ProductEntity p20 = new ProductEntity("Рёбра Слово Мясника свинные, гриль", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/167196_1623671804.jpg",
                100L, 182L);

        ProductEntity p21 = new ProductEntity("Мякоть бедра Мираторг из говядины", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/743bed11dec5f138_4630016235941-1.jpg",
                100L, 705L);

        ProductEntity p22 = new ProductEntity("Печень цеплёнка бройлера Наша птичка", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/3aac2b0a-9f9a-11ec-b967-08c0eb32008b_719590546.jpg",
                100L, 144L);
        //
        ProductEntity p23 = new ProductEntity("Вода Святой источник без газа", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/170756323_640519044.jpg",
                100L, 153L);

        ProductEntity p24 = new ProductEntity("Вода Липецкая росинка без газа", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/b86c5d851cf12cb8_4601025115609.jpg",
                100L, 112L);

        ProductEntity p25 = new ProductEntity("Вода Святой Источник без газа", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/180195_722042624.jpg",
                100L, 43L);

        ProductEntity p26 = new ProductEntity("Напиток Starbar Soda water", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/76c1a0c645089128_4600286103363.jpg",
                100L, 249L);

        //
        ProductEntity p27 = new ProductEntity("Шоколад Милка молочный с фундуком", "Описание продукта",
                "https://cm.samokat.ru/processed/m/product_card/0025cb80-df43-4e4f-a682-a353e66d7c5e.jpg",
                100L, 190L);
        ProductEntity p28 = new ProductEntity("Шоколад Милка молочный", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/ca68743854e0bafe_7622201769291-2.jpg",
                100L,  182L);

        ProductEntity p29 = new ProductEntity("Яйцо шоколадное Kinder Сюрприз", "Описание продукта",
                "https://cm.samokat.ru/processed/m/product_card/141f32b8-9410-4916-b568-87d94e1b913d.jpg",
                100L, 129L);

        ProductEntity p30 = new ProductEntity("Шоколад Kinder Фигурный молочный", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/165ba98f9d7bffd6_80969112-1.jpg",
                100L, 139L);
        ProductEntity p31 = new ProductEntity("Шоколад Милка с печеньем", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/f17b03ec87aa5320_7622210451255.jpg",
                100L, 190L);
        //
        ProductEntity p32 = new ProductEntity("Чипсы Lays из печи нежный сыр", "Описание продукта",
                "https://cm.samokat.ru/processed/m/product_card/4f603584-a70b-46e7-b8ed-1bd011ad3c79.jpg",
                100L, 102L);
        ProductEntity p33 = new ProductEntity("Чипсы Lays рифленые сметана и лук", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/ee23e3228c717463_4690388050474.jpg",
                100L,  215L);

        ProductEntity p34 = new ProductEntity("Чипсы Lays зеленый лук", "Описание продукта",
                "https://cm.samokat.ru/processed/m/product_card/37a5f1f8-a8bc-45ca-990d-e811a6a9f073.jpg",
                100L, 150L);

        ProductEntity p35 = new ProductEntity("Чипсы Lays рифленые лобстер", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/349603eec48328c9_4690388111465.jpg",
                100L, 177L);
        ProductEntity p36 = new ProductEntity("Чипсы Lays рифленые паприка", "Описание продукта",
                "https://cm.samokat.ru/processed/m/product_card/224f7330-8ee8-4514-85a8-de2ff2084ef1.jpg",
                100L, 177L);
        //
        ProductEntity p37 = new ProductEntity("Макароны Самокат с томатом", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/eef66516a6288c67_4620005842113-1.jpg",
                100L, 149L);
        ProductEntity p38 = new ProductEntity("Макароны Самокат со шпинатом", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/773de7d153852a79_4620005842106-1.jpg",
                100L,  154L);

        ProductEntity p39 = new ProductEntity("Макароны Самокат Fusilli спирали", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/38019f76a2e6ca38_4607072719876.jpg",
                100L, 69L);

        ProductEntity p40 = new ProductEntity("Макароны Самокат гнезда", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/b752cb87b1b1de59_4607072719913-1.jpg",
                100L, 99L);
        //
        ProductEntity p41 = new ProductEntity("Молоко пастеризованное Самокат", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/88970d79dc387229_2199603000000.jpg",
                100L, 64L);
        ProductEntity p42 = new ProductEntity("Молоко Агуша ультрапастеризованное", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/a6c6cad1508e335a_4690228030161.jpg",
                100L,  139L);

        ProductEntity p43 = new ProductEntity("Молоко Домик в деревне", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/158334_425819778.jpg",
                100L, 74L);

        ProductEntity p44 = new ProductEntity("Молоко Parmalat пастеризованное", "Описание продукта",
                "https://cm.samokat.ru/processed/m/public/711937cd1532ce68_4601662000023.jpg",
                100L, 108L);
        ProductEntity p45 = new ProductEntity("Молоко Вкуснотеево пастеризованное", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/152455_1394027930.jpg",
                100L, 94L);

        ProductEntity p46 = new ProductEntity("Молоко Авида пастеризованное", "Описание продукта",
                "https://cm.samokat.ru/processed/m/original/301285013_494472226.jpg",
                100L, 88L);

        GroupEntity g1 = new GroupEntity("Все товары", "https://cm.samokat.ru/processed/category/4ccedaf7-4296-42fe-9519-6f04e78704ab.jpg");
        g1.setProducts(Stream.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
                p11, p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30,
                p31, p32, p33, p34, p35, p36, p37, p38, p39, p40,
                p41, p42, p43, p44, p45, p46).collect(Collectors.toSet()));

        GroupEntity g2 = new GroupEntity("Готовая еда", "https://cm.samokat.ru/processed/category/8d121a12-45a6-4ec3-b195-c62c818e162d.jpg");
        g2.setProducts(Stream.of(p1, p2, p3, p4, p5).collect(Collectors.toSet()));

        GroupEntity g3 = new GroupEntity("Хлеб и выпечка", "https://cm.samokat.ru/processed/original/160441_349334568.jpg");
        g3.setProducts(Stream.of(p6, p7, p8, p9, p10, p11, p12).collect(Collectors.toSet()));

        GroupEntity g4 = new GroupEntity("Овощи и фрукты", "https://cm.samokat.ru/processed/category/1694187180-pic1.jpg");
        g4.setProducts(Stream.of(p13, p14, p15, p16, p17).collect(Collectors.toSet()));

        GroupEntity g5 = new GroupEntity("Мясо и рыба", "https://cm.samokat.ru/processed/public/b6db88b1e3657695______________7.jpg");
        g5.setProducts(Stream.of(p19, p19, p20, p21, p22).collect(Collectors.toSet()));

        GroupEntity g6 = new GroupEntity("Вода и напитки", "https://cm.samokat.ru/processed/original/88488_1136633278.jpg");
        g6.setProducts(Stream.of(p23, p24, p25, p26).collect(Collectors.toSet()));

        GroupEntity g7 = new GroupEntity("Сладкое", "https://cm.samokat.ru/processed/category/186dd558-e7ec-4309-a576-abc39bc18202.jpg");
        g7.setProducts(Stream.of(p27, p28, p29, p30, p31).collect(Collectors.toSet()));

        GroupEntity g8 = new GroupEntity("Чипсы и сухарики", "https://cm.samokat.ru/processed/public/fd4a6ecb80cdacae_pepsico_3.jpg");
        g8.setProducts(Stream.of(p32, p33, p34, p35, p36).collect(Collectors.toSet()));

        GroupEntity g9 = new GroupEntity("Бакалея", "https://cm.samokat.ru/processed/original/153793_1354391565.jpg");
        g9.setProducts(Stream.of(p37, p38, p39, p40).collect(Collectors.toSet()));

        GroupEntity g10 = new GroupEntity("Молоко, яйца и сыр", "https://cm.samokat.ru/processed/public/9ecb524e05b2b4b3_________________________.jpg");
        g10.setProducts(Stream.of(p41, p42, p43, p44, p45, p46).collect(Collectors.toSet()));

        groupRepository.saveAll(Arrays.asList(g1, g2, g3, g5, g6, g7, g8, g9, g10));

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

        courierRepository.save(new CourierEntity("Данил Вонтроп", "wanropp@email.com", "w12345"));
        assemblerRepository.save(new AssemblerEntity("Марина Ковалева", "marina@email.com", "loqiemean12345"));
    }
}
