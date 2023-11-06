package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.AddressEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.AddressDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO toDTO(AddressEntity entity);
}
