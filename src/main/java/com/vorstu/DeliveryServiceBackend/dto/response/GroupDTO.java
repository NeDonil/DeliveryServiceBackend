package com.vorstu.DeliveryServiceBackend.dto.response;

import com.vorstu.DeliveryServiceBackend.db.entities.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupDTO {
    private Long id;
    private String name;
    private String url;
}
