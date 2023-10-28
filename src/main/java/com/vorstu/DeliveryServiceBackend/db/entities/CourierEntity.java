package com.vorstu.DeliveryServiceBackend.db.entities;

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

}
