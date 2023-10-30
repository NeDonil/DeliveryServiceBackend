package com.vorstu.DeliveryServiceBackend.db.repositories;

import com.vorstu.DeliveryServiceBackend.db.entities.CourierEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends CrudRepository<CourierEntity, Long> {
    @Query("SELECT c FROM CourierEntity c WHERE c.credentials.email=:email")
    CourierEntity findUserByEmail(@Param("email") String email);
}