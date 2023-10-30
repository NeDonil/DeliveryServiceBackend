package com.vorstu.DeliveryServiceBackend.db.repositories;

import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssemblerRepository extends CrudRepository<AssemblerEntity, Long> {
    @Query("SELECT a FROM AssemblerEntity a WHERE a.credentials.email=:email")
    AssemblerEntity findUserByEmail(@Param("email") String email);
}
