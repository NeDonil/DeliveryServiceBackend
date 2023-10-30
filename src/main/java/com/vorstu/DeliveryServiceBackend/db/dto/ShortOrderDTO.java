package com.vorstu.DeliveryServiceBackend.db.dto;

import com.vorstu.DeliveryServiceBackend.db.entities.AddressEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ShortOrderDTO {
    private Long id;
    private String comment;
    private AddressEntity address;
    private Long prouctCount;

    public static ShortOrderDTO fromEntity(OrderEntity entity){
        long productCount = entity.getItems().size();
        return new ShortOrderDTO(entity.getId(), entity.getComment(), entity.getAddress(), productCount);
    }
}
