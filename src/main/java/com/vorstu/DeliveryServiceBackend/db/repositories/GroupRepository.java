package com.vorstu.DeliveryServiceBackend.db.repositories;

import com.vorstu.DeliveryServiceBackend.db.entities.GroupEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
    @Query("SELECT g.products FROM GroupEntity g WHERE g.id=:group_id")
    List<ProductEntity> findProductsInGroup(@Param("group_id") Long group_id);
}
