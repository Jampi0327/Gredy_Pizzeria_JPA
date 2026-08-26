package com.Practica.JPA.persistence.repository;

import com.Practica.JPA.persistence.entity.PizzaEntity;
import java.util.Optional;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity ,Integer> {

    List<PizzaEntity> findAllByAvaiableTrueOrderByPrice();
    Optional<PizzaEntity> findFirstByAvaiableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvaiableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvaiableTrueAndDescriptionNotContainingIgnoreCase(String description);
    List<PizzaEntity> findTop3ByAvaiableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);
    int countByVeganTrue();
}
