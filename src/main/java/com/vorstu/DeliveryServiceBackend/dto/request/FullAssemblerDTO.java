package com.vorstu.DeliveryServiceBackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullAssemblerDTO {
    @NotBlank
    @Size(min = 5, max = 255)
    private String fio;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 5, max = 20)
    private String password;

}
