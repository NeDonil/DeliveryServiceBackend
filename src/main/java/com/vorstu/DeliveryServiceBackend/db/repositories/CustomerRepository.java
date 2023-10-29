package com.vorstu.DeliveryServiceBackend.db.repositories;

import com.vorstu.DeliveryServiceBackend.db.entities.CustomerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    @Query("SELECT c FROM CustomerEntity c WHERE c.credentials.email=:email")
    CustomerEntity findUserByEmail(@Param("email") String email);
}
