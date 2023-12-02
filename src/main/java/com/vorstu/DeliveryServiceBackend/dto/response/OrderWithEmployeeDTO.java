package com.vorstu.DeliveryServiceBackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderWithEmployeeDTO {
    BaseUserDTO employee;
    OrderDTO order;
}
