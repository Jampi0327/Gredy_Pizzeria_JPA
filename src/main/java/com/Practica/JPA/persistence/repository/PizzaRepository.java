package com.Practica.JPA.persistence.repository;

import com.Practica.JPA.persistence.entity.PizzaEntity;
import com.Practica.JPA.service.dto.updatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvaiableTrueOrderByPrice();
    Optional<PizzaEntity> findFirstByAvaiableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvaiableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvaiableTrueAndDescriptionNotContainingIgnoreCase(String description);
    List<PizzaEntity> findTop3ByAvaiableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);
    int countByVeganTrue();

    @Modifying
    @Query(value = "UPDATE pizza " +
            "SET price = :#{#newPizzaPrice.newPrice} " +
            "WHERE id_pizza = :#{#newPizzaPrice.pizzaId}", nativeQuery = true)
    void updatePrice(@Param("newPizzaPrice") updatePizzaPriceDto newPizzaPrice);
}