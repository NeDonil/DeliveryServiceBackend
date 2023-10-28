package com.vorstu.DeliveryServiceBackend.db.entities;


import com.vorstu.DeliveryServiceBackend.db.entities.auth.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
@Getter
@Setter
@RequiredArgsConstructor
public class AdminEntity extends BaseUser {
    public AdminEntity(String fio, String email, String password){
        super(fio, email, password, UserRole.ADMIN);
    }
}
