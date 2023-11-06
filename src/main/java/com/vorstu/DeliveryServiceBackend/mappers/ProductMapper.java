package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(ProductEntity entity);
}
