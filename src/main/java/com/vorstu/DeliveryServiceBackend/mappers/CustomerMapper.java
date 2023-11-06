package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.CustomerEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AddressListMapper.class)
public interface CustomerMapper {
    @Mapping(target = "email", source = "entity.credentials.email")
    CustomerDTO toDTO(CustomerEntity entity);
}
