package com.vorstu.DeliveryServiceBackend.dto.request;

import lombok.Data;

@Data
public class ShortOrderItemDTO {
    private Long id;
    private Long count;
    private ShortProductDTO product;
}
