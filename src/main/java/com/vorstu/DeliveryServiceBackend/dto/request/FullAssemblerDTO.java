package com.vorstu.DeliveryServiceBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullAssemblerDTO {
    @NotBlank
    private String fio;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}
