package com.vorstu.DeliveryServiceBackend.messages;

import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMessage {
    private Long code;
    private OrderDTO order;
}
