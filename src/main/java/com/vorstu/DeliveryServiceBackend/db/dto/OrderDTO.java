package com.vorstu.DeliveryServiceBackend.db.dto;

import com.vorstu.DeliveryServiceBackend.db.entities.AddressEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class OrderDTO {
    private Long id;
    private String comment;
    private AddressEntity address;
    private List<OrderItemEntity> items;

    public static OrderDTO fromEntity(OrderEntity entity){
        return new OrderDTO(entity.getId(), entity.getComment(), entity.getAddress(), entity.getItems());
    }
}
