package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.AssemblerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssemblerMapper {
    AssemblerDTO toDTO(AssemblerEntity entity);
}
