package com.vorstu.DeliveryServiceBackend.dto.request;

import lombok.Data;

@Data
public class ShortOrderItemDTO {
    public ShortOrderItemDTO(Long count, ShortProductDTO product){
        this.count = count;
        this.product = product;
    }
    private Long id;
    private Long count;
    private ShortProductDTO product;
}
