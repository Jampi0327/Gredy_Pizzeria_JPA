package com.Practica.JPA.persistence.repository;

import com.Practica.JPA.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity ,Integer> {

    List<PizzaEntity> findAllByAvaiableTrueOrderByPrice();
    PizzaEntity findAllByAvaiableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvaiableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvaiableTrueAndDescriptionNotContainingIgnoreCase(String description);

}
