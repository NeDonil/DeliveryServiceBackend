package com.vorstu.DeliveryServiceBackend.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ShortOrderDTO {
    public ShortOrderDTO(ShortAddressDTO address, List<ShortOrderItemDTO> items){
        this.address = address;
        this.items = items;
    }

    private String comment;
    private ShortAddressDTO address;
    private List<ShortOrderItemDTO> items;
}
