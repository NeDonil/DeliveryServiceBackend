package com.vorstu.DeliveryServiceBackend.dto.response;

import com.vorstu.DeliveryServiceBackend.db.entities.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private String value;

    public static AddressDTO fromEntity(AddressEntity entity){
        return new AddressDTO(entity.getId(), entity.getValue());
    }
}
