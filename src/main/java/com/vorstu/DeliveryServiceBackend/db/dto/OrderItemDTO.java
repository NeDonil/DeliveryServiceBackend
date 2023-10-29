package com.vorstu.DeliveryServiceBackend.db.dto;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderItemEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class OrderItemDTO {
    private Long id;
    private Long count;
    private ShortProductDTO shortProduct;

    public static OrderItemDTO fromEntity(OrderItemEntity entity){
        return new OrderItemDTO(entity.getId(), entity.getCount(), ShortProductDTO.fromEntity(entity.getProduct()));
    }
}
