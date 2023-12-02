package com.vorstu.DeliveryServiceBackend.messages;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.dto.response.BaseUserDTO;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMessage {

    private OrderAction code;
    private BaseUserDTO employee;
    private OrderDTO order;
}
