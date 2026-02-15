package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.GroupEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.GroupDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupDTO toDTO(GroupEntity entity);

    List<GroupDTO> toDTOList(List<GroupEntity> groups);
}
