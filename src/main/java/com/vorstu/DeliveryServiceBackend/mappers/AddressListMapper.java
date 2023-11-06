package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.AddressEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.AddressDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface AddressListMapper {
    List<AddressDTO> toDTOList(List<AddressEntity> addresses);
}
