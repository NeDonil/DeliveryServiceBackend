package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemListMapper.class, AddressMapper.class})
public interface OrderMapper {
    OrderDTO toDTO(OrderEntity entity);
}
