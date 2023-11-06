package com.vorstu.DeliveryServiceBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FullAssemblerDTO {
    private String fio;
    private String email;
    private String password;

}
