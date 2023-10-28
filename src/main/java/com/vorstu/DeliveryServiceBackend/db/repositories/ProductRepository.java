package com.vorstu.DeliveryServiceBackend.db.repositories;

import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
