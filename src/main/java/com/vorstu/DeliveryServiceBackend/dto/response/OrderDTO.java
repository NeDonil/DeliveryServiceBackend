package com.vorstu.DeliveryServiceBackend.dto.response;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String comment;
    private AddressDTO address;
    private List<OrderItemDTO> items;

    public static OrderDTO fromEntity(OrderEntity entity){
        return new OrderDTO(entity.getId(), entity.getComment(),
                entity.getAddress() != null ? AddressDTO.fromEntity(entity.getAddress()): null,
                entity.getItems()
                        .stream()
                        .map(OrderItemDTO::fromEntity)
                        .collect(Collectors.toList())
        );
    }
}
