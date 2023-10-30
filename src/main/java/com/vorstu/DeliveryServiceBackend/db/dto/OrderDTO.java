package com.vorstu.DeliveryServiceBackend.db.dto;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

public enum OrderDTO{
    ;

    private interface Id{Long getId(); }
    private interface Comment{String getComment(); }
    private interface Address{String getAddress();}
    private interface ShortItems{List<OrderItemDTO.Short.Response> getShortItems();}

    public enum Short {;

        @Value public static class Response implements Id, Comment, Address, ShortItems {
            Long id;
            String comment;
            String address;
            List<OrderItemDTO.Short.Response> shortItems;
            public static Response fromEntity(OrderEntity entity){
                List<OrderItemDTO.Short.Response> shortItems = entity.getItems()
                        .stream()
                        .map(OrderItemDTO.Short.Response::fromEntity)
                        .collect(Collectors.toList());
                String address =  entity.getAddress() == null ? "" : entity.getAddress().getValue(); // Todo
                return new Response(entity.getId(), entity.getComment(), address, shortItems);
            }

        }
    }
}
