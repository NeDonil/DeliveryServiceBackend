package com.vorstu.DeliveryServiceBackend.db.repositories;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    @Query("SELECT o FROM OrderEntity o WHERE o.customer.id=:customer_id AND o.status=com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus.MAKING")
    OrderEntity findCurrentOrderByCustomerId(@Param("customer_id") Long customer_id);
}
