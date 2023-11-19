package com.vorstu.DeliveryServiceBackend.db.repositories;

import com.vorstu.DeliveryServiceBackend.db.entities.AddressEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
    @Query("SELECT c.addresses FROM CustomerEntity c WHERE c.credentials.email=:customer_email")
    List<AddressEntity> findAllAddressesByEmail(@Param("customer_email") String email);
}
