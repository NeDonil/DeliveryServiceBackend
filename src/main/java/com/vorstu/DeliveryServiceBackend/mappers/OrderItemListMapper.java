package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderItemEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderItemDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderItemListMapper {
    List<OrderItemDTO> toDTOList(List<OrderItemEntity> items);
}
