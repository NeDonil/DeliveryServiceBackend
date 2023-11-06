package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderItemEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderItemMapper {
    OrderItemDTO toDTO(OrderItemEntity entity);
}
