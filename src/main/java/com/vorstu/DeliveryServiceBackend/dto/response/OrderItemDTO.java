package com.vorstu.DeliveryServiceBackend.dto.response;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Long count;
    private ProductDTO product;

    public static OrderItemDTO fromEntity(OrderItemEntity entity){
        return new OrderItemDTO(entity.getId(), entity.getCount(),
                ProductDTO.fromEntity(entity.getProduct())
        );
    }
}
