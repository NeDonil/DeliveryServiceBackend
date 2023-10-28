package com.vorstu.DeliveryServiceBackend.db.entities;


import com.vorstu.DeliveryServiceBackend.db.entities.auth.UserRole;
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

    public CustomerEntity(String fio, String email, String password){
        super(fio, email, password, UserRole.CUSTOMER);
    }
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "customer")
    private List<AddressEntity> addresses;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "payment_data_id", referencedColumnName = "id")
    private PaymentDataEntity paymentData;
}
