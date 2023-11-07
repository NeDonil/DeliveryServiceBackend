package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.AssemblerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = AssemblerMapper.class)
public interface AssemblerListMapper {
    List<AssemblerDTO> toDTOList(List<AssemblerEntity> assemblers);
}
