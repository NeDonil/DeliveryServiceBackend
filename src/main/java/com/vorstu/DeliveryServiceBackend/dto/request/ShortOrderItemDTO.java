package com.vorstu.DeliveryServiceBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ShortOrderItemDTO {
    private Long id;
    private Long count;
    private ShortProductDTO product;
}
