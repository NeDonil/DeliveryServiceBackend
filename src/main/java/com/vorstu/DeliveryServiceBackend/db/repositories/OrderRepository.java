package com.vorstu.DeliveryServiceBackend.db.repositories;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    @Query("SELECT o FROM OrderEntity o WHERE o.customer.id=:customer_id AND o.status=com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus.MAKING")
    OrderEntity findCurrentOrderByCustomerId(@Param("customer_id") Long customer_id);

    @Query("SELECT o FROM OrderEntity o WHERE o.customer.id=:customer_id AND NOT o.status=com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus.MAKING")
    List<OrderEntity> findAllOrdersByCustomerId(@Param("customer_id") Long customer_id);

    @Query("SELECT o FROM OrderEntity o WHERE o.status=:status")
    List<OrderEntity> findAllOrdersByStatus(@Param("status") OrderStatus status);

    @Query("SELECT o FROM OrderEntity o WHERE o.status IN :statuses AND (o.assembler.id=:employee_id OR o.courier.id=:employee_id)")
    Optional<OrderEntity> findCurrentEmployeeOrder(@Param("employee_id") Long employee_id, @Param("statuses") List<OrderStatus> statuses);
}
