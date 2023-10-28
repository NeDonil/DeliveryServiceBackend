package com.vorstu.DeliveryServiceBackend.db.entities;


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
    public AdminEntity(String fio){
        super(fio);
    }
}
