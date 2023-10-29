package com.vorstu.DeliveryServiceBackend.db.dto;

import com.vorstu.DeliveryServiceBackend.db.entities.AddressEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CustomerDTO {
    private Long id;
    private String fio;
    private String email;
    private List<AddressEntity> addresses;

    public static CustomerDTO fromEntity(CustomerEntity entity){
        return new CustomerDTO(entity.getId(), entity.getFio(), entity.getCredentials().getEmail(), entity.getAddresses());
    }
}
