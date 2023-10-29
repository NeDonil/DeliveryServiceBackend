package com.vorstu.DeliveryServiceBackend.db.dto;

import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShortProductDTO {
    private Long id;
    private String title;
    private Long count;
    private Long price;

    public static ShortProductDTO fromEntity(ProductEntity entity){
        return new ShortProductDTO(entity.getId(), entity.getTitle(), entity.getCount(), entity.getPrice());
    }
}
