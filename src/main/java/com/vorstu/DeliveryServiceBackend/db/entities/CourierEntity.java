package com.vorstu.DeliveryServiceBackend.db.entities;

import com.vorstu.DeliveryServiceBackend.db.entities.auth.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "couriers")
@Getter
@Setter
@NoArgsConstructor
public class CourierEntity extends BaseUser {
    public CourierEntity(String fio, String email, String password){
        super(fio, email, password, UserRole.COURIER);
    }
}
