package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.BaseUser;
import com.vorstu.DeliveryServiceBackend.dto.response.BaseUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseUserMapper {
    BaseUserDTO toDTO(BaseUser entity);
}
