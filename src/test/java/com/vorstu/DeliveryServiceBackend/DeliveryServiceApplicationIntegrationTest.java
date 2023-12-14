package com.vorstu.DeliveryServiceBackend;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.*;
import com.vorstu.DeliveryServiceBackend.db.repositories.ProductRepository;
import com.vorstu.DeliveryServiceBackend.dto.request.*;
import com.vorstu.DeliveryServiceBackend.dto.response.AddressDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import com.vorstu.DeliveryServiceBackend.services.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // deactivate the default behaviour
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeliveryServiceApplicationIntegrationTest {
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );
    static AddressDTO address;
    static CustomerEntity customer;
    static final String customerEmail = "customer@email.com";
    static AssemblerEntity assembler;
    static final String assemblerEmail = "assembler@email.com";
    static CourierEntity courier;
    static final String courierEmail = "courier@email.com";
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AssemblerService assemblerService;
    @Autowired
    private CourierService courierService;
    @Autowired
    private AuthService authService;
    @Autowired
    private ProductRepository productRepository;

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", postgres::getDriverClassName);
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    @Order(1)
    void createDataTest(){
        assertDoesNotThrow(() ->
            authService.register(
                    new NewUserDTO(customerEmail, "testuser", "t12345")
            )
        );

        address = customerService.createAddress(customerEmail, "TestAddress");
        assertNotNull(address);

        assertNotNull(
                assemblerService.createAssembler(
                    new FullAssemblerDTO("assembler", assemblerEmail, "a12345")
                )
        );

        assertNotNull(
                courierService.createCourier(
                    new FullCourierDTO("courier", courierEmail, "c12345")
                )
        );
    }

    @Test
    @Order(2)
    void changeOrder(){
        ProductEntity product = productRepository.save(
                new ProductEntity("title", "desc", "", 1L, 1L));

        ShortProductDTO trivialProductDTO = new ShortProductDTO();
        trivialProductDTO.setId(product.getId());

        ShortOrderItemDTO trivialOrderItemDTO = new ShortOrderItemDTO();
        trivialOrderItemDTO.setCount(1L);
        trivialOrderItemDTO.setProduct(trivialProductDTO);

        List<ShortOrderItemDTO> items = Arrays.asList(trivialOrderItemDTO);

        assertNotNull(customerService.getCurrentOrder(customerEmail));

        ShortAddressDTO trivialAddress = new ShortAddressDTO();
        trivialAddress.setId(address.getId());

        ShortOrderDTO trivialOrderDTO = new ShortOrderDTO();
        trivialOrderDTO.setAddress(trivialAddress);
        trivialOrderDTO.setItems(items);

        OrderDTO updatedOrder = customerService.updateCurrentOrder(customerEmail, trivialOrderDTO);

        assertEquals(updatedOrder, customerService.getCurrentOrder(customerEmail));
    }

    @Test
    @Order(3)
    void makeOrder(){
        OrderDTO order = customerService.getCurrentOrder(customerEmail);
        customerService.doAction(customerEmail, order.getId(), OrderAction.MAKE);
        assertNotEquals(order, customerService.getCurrentOrder(customerEmail));
        assertNotNull(assemblerService.getOrders().get(0));
    }

    @Test
    @Order(4)
    void toAssemblyTest(){
        OrderDTO placedOrder = assemblerService.getOrders().get(0);
        assertNotNull(placedOrder);

        assemblerService.doAction(assemblerEmail, placedOrder.getId(), OrderAction.TO_ASSEMBLY);
        placedOrder.setStatus(OrderStatus.ASSEMBLING.toString());

        assertEquals(placedOrder, assemblerService.getCurrentOrder(assemblerEmail).getOrder());
    }

    @Test
    @Order(5)
    void toAssembledTest(){
        OrderDTO order = assemblerService.getCurrentOrder(assemblerEmail).getOrder();
        assertNotNull(order);
        assemblerService.doAction(assemblerEmail, order.getId(), OrderAction.TO_ASSEMBLED);

        assertNotEquals(order, assemblerService.getCurrentOrder(assemblerEmail).getOrder());
        assertNotNull(courierService.getOrders().get(0));
    }

    @Test
    @Order(6)
    void toDeliveryTest(){
        OrderDTO assembledOrder = courierService.getOrders().get(0);
        assertNotNull(assembledOrder);

        courierService.doAction(courierEmail, assembledOrder.getId(), OrderAction.TO_DELIVERY);

        assembledOrder.setStatus(OrderStatus.DELIVERING.toString());
        assertEquals(assembledOrder, courierService.getCurrentOrder(courierEmail).getOrder());
    }

    @Test
    @Order(7)
    void toDeliveredTest(){
        OrderDTO order = courierService.getCurrentOrder(courierEmail).getOrder();
        assertNotNull(order);
        courierService.doAction(courierEmail, order.getId(), OrderAction.TO_DELIVERED);

        assertNotEquals(order, courierService.getCurrentOrder(courierEmail).getOrder());
    }

    @Test
    @Order(8)
    void orderDeliveredSuccessTest(){
        OrderDTO order = customerService.getCustomerOrders(customerEmail).get(0);
        assertEquals(order.getStatus(), OrderStatus.DELIVERED.toString());

    }
}
