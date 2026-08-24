package com.Practica.JPA.persistence.repository;

import com.Practica.JPA.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity ,Integer> {


}
