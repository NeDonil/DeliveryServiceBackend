package com.vorstu.DeliveryServiceBackend.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class ShortOrderDTO {

    private String comment;

    private ShortAddressDTO address;

    private List<ShortOrderItemDTO> items;
}
