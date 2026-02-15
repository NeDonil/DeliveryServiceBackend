package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.CourierEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.CourierDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourierMapper {
    CourierDTO toDTO(CourierEntity entity);

    List<CourierDTO> toDTOList(List<CourierEntity> couriers);
}
