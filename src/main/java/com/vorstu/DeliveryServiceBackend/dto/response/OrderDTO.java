package com.vorstu.DeliveryServiceBackend.dto.response;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String comment;
    private AddressDTO address;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private String status;
    private List<OrderItemDTO> items;

}
