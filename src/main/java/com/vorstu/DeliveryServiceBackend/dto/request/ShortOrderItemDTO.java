package com.vorstu.DeliveryServiceBackend.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class ShortOrderItemDTO {
    private Long id;

    @NotBlank
    @Positive
    private Long count;

    @NotBlank
    private ShortProductDTO product;
}
