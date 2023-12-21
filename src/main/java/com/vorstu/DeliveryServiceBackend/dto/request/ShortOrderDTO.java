package com.vorstu.DeliveryServiceBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class ShortOrderDTO {

    private String comment;

    private ShortAddressDTO address;

    private List<ShortOrderItemDTO> items;
}
