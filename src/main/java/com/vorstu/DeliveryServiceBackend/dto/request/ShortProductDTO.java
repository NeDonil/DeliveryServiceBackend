package com.vorstu.DeliveryServiceBackend.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShortProductDTO {
    @NotBlank
    private Long id;
}
