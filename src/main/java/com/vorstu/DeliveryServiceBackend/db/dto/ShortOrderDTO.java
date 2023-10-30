package com.vorstu.DeliveryServiceBackend.db.dto;

import com.vorstu.DeliveryServiceBackend.db.entities.AddressEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class ShortOrderDTO {
    private Long id;
    private String comment;
    private AddressEntity address;
    private List<OrderItemDTO.Short.Response> items;

    public static ShortOrderDTO fromEntity(OrderEntity entity){
        List<OrderItemDTO.Short.Response> items = entity.getItems()
                .stream()
                .map(OrderItemDTO.Short.Response::fromEntity)
                .collect(Collectors.toList());
        return new ShortOrderDTO(entity.getId(), entity.getComment(), entity.getAddress(), items);
    }
}
