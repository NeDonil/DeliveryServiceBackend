package com.vorstu.DeliveryServiceBackend.dto.response;

import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String title;
    private String photo;
    private Long count;
    private Long price;

    public static ProductDTO fromEntity(ProductEntity entity){
        return new ProductDTO(entity.getId(), entity.getTitle(), entity.getPhoto(), entity.getCount(), entity.getPrice());
    }
}
