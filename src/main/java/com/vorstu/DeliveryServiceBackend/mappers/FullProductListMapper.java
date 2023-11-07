package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.FullProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = FullProductMapper.class)
public interface FullProductListMapper {
    List<FullProductDTO> toDTOList(List<ProductEntity> products);
}
