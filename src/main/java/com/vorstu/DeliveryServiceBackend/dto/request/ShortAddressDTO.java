package com.vorstu.DeliveryServiceBackend.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class ShortAddressDTO {
    @NotBlank
    @Positive
    private Long id;
}
