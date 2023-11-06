package com.vorstu.DeliveryServiceBackend.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ShortOrderDTO {
    private String comment;
    private ShortAddressDTO address;
    private List<ShortOrderItemDTO> items;
}
