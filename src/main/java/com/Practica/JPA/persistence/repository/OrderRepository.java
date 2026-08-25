package com.Practica.JPA.persistence.repository;

import com.Practica.JPA.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;


public interface OrderRepository extends ListCrudRepository<OrderEntity , Integer> {

}
