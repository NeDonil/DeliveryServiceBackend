package com.vorstu.DeliveryServiceBackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseUserDTO {
    private Long id;
    private String fio;
}
