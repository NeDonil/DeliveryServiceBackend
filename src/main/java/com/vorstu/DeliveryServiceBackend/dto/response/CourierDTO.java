package com.vorstu.DeliveryServiceBackend.dto.response;

import com.vorstu.DeliveryServiceBackend.db.entities.CourierEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourierDTO {
    private Long id;
    private String fio;

    public static CourierDTO fromEntity(CourierEntity entity){
        return new CourierDTO(entity.getId(), entity.getFio());
    }
}
