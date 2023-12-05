package com.vorstu.DeliveryServiceBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDTO {
    private String email;
    private String fio;
    private String password;

}
