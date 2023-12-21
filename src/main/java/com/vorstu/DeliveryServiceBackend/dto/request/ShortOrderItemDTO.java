package com.vorstu.DeliveryServiceBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
public class ShortOrderItemDTO {
    private Long id;

    @NotBlank
    private Long count;

    @NotBlank
    private ShortProductDTO product;
}
