package com.Practica.JPA.persistence.repository;

import com.Practica.JPA.persistence.entity.PizzaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity, Integer >{

    Page<PizzaEntity> findByAvaiableTrue(Pageable pageable);

}
