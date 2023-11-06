package com.vorstu.DeliveryServiceBackend.dto.response;

import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssemblerDTO {
    private Long id;
    private String fio;

    public static AssemblerDTO fromEntity(AssemblerEntity entity){
        return new AssemblerDTO(entity.getId(), entity.getFio());
    }
}
