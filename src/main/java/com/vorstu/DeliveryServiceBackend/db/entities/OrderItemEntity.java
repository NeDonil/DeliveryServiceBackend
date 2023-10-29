package com.vorstu.DeliveryServiceBackend.db.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @NotNull
    private Long count;
}
