package com.vorstu.DeliveryServiceBackend.db.dto;

import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.function.Function;

public enum AssemblerDTO{
    ;

    private interface Id{Long getId(); }
    private interface Fio{String getFio(); }
    private interface Email{String getEmail();}
    private interface Password{String getPassword();}

    @Value public static class Request implements Fio, Email, Password{
        String fio;
        String email;
        String password;
    }

    @Value public static class Response implements Id, Email{
        Long id;
        String email;

        public static Response fromEntity(AssemblerEntity entity){
            return new Response(entity.getId(), entity.getFio());
        }
    }
}
