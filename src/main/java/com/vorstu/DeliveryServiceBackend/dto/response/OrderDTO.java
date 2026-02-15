package com.vorstu.DeliveryServiceBackend.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String comment;
    private AddressDTO address;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private String status;
    private List<OrderItemDTO> items;

    public OrderDTO(Long id) {
        this.id = id;
    }
}
