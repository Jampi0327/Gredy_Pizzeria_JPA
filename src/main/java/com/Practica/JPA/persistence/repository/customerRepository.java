package com.Practica.JPA.persistence.repository;

import com.Practica.JPA.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface customerRepository extends ListCrudRepository<CustomerEntity,Integer> {
    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phone")
    CustomerEntity findByPhone(@Param("phone")String phone);


}
