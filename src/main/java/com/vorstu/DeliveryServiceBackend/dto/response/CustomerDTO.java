package com.vorstu.DeliveryServiceBackend.dto.response;

import com.vorstu.DeliveryServiceBackend.db.entities.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String fio;
    private String email;
    private List<AddressDTO> addresses;

    public static CustomerDTO fromEntity(CustomerEntity entity){
        return new CustomerDTO(entity.getId(),
                entity.getFio(),
                entity.getCredentials().getEmail(),
                entity.getAddresses() == null ? null : entity.getAddresses()
                        .stream()
                        .map(AddressDTO::fromEntity)
                        .collect(Collectors.toList())
        );
    }
}
