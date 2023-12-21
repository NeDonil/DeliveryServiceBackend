package com.vorstu.DeliveryServiceBackend.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class ShortOrderItemDTO {
    private Long id;

    @NotBlank
    @Positive
    private Long count;

    @NotBlank
    private ShortProductDTO product;
}
