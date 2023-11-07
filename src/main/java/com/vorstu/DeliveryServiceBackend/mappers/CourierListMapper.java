package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.CourierEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.CourierDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = CourierMapper.class)
public interface CourierListMapper {
    List<CourierDTO> toDTOList(List<CourierEntity> couriers);
}
