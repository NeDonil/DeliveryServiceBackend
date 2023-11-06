package com.vorstu.DeliveryServiceBackend.dto.response;

import com.vorstu.DeliveryServiceBackend.db.entities.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupDTO {
    private Long id;
    private String name;

    public static GroupDTO fromEntity(GroupEntity entity){
        return new GroupDTO(entity.getId(), entity.getName());
    }
}
