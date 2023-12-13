package com.vorstu.DeliveryServiceBackend.db.repositories;

import com.vorstu.DeliveryServiceBackend.db.entities.AddressEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.AdminEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends CrudRepository<AdminEntity, Long> {
    @Query("SELECT a FROM AdminEntity a WHERE a.credentials.email=:admin_email")
    AdminEntity findAdminByEmail(@Param("admin_email") String email);

}