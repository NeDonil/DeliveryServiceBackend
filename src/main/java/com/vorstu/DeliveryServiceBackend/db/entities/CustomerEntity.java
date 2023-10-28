package com.vorstu.DeliveryServiceBackend.db.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customers")
@NoArgsConstructor
public class CustomerEntity extends BaseUser {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<AddressEntity> addresses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_data_id", referencedColumnName = "id")
    private PaymentDataEntity paymentData;
}
