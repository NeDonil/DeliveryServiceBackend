package com.vorstu.DeliveryServiceBackend.db.dto;

import com.vorstu.DeliveryServiceBackend.db.entities.GroupEntity;
import lombok.Value;

public enum GroupDTO{
    ;
    private interface Id{Long getId(); }
    private interface Name{String getName(); }

    @Value
    public static class Response implements Id, Name{
        Long id;
        String name;
        public static Response fromEntity(GroupEntity entity){
            return new Response(entity.getId(), entity.getName());
        }
    }
}
