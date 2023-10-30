package com.vorstu.DeliveryServiceBackend.db.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne()
    private AddressEntity address;

    @ManyToOne()
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;

    @ManyToOne()
    @JoinColumn(name = "assembler_id", referencedColumnName = "id")
    private AssemblerEntity assembler;

    @ManyToOne()
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    private CourierEntity courier;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItemEntity> items;

    public OrderEntity(CustomerEntity customer){
        this.status = OrderStatus.MAKING;
        this.customer = customer;
        this.items = new ArrayList<>();
    }
}
