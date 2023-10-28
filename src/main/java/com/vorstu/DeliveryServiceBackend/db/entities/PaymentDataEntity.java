package com.vorstu.DeliveryServiceBackend.db.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="payments")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class PaymentDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long balance;
}
