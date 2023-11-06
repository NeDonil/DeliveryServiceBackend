package com.vorstu.DeliveryServiceBackend.dto.response;

import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String title;
    private Long count;
    private Long price;

    public static ProductDTO fromEntity(ProductEntity entity){
        return new ProductDTO(entity.getId(), entity.getTitle(), entity.getCount(), entity.getPrice());
    }
}
