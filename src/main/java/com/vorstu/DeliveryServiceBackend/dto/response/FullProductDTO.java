package com.vorstu.DeliveryServiceBackend.dto.response;

import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class FullProductDTO {
    private Long id;
    private String title;
    private String photo;
    private String description;
    private Long count;
    private Long price;

    public static FullProductDTO fromEntity(ProductEntity entity){
        return new FullProductDTO(entity.getId(), entity.getTitle(), entity.getPhoto(),
                entity.getDescription(), entity.getCount(), entity.getPrice());
    }
}
