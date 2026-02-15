package com.vorstu.DeliveryServiceBackend.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class ShortAddressDTO {
    @NotBlank
    @Positive
    private Long id;
}
