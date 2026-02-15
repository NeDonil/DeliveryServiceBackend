package com.vorstu.DeliveryServiceBackend.mappers;

import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import com.vorstu.DeliveryServiceBackend.dto.response.FullProductDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FullProductMapper {
    FullProductDTO toDTO(ProductEntity entity);

    List<FullProductDTO> toDTOList(List<ProductEntity> products);
}
