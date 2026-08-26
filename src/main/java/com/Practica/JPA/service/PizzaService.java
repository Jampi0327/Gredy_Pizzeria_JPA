package com.Practica.JPA.service;

import com.Practica.JPA.persistence.entity.PizzaEntity;
import com.Practica.JPA.persistence.repository.PizzaPagSortRepository;
import com.Practica.JPA.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository  pizzaPagSortRepository;

    @Autowired //se encarga de la inyeccion de dependencias de todo esos componentes
    public PizzaService (PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository){
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }
    //recupera todas las pizza de mi base de datos a traves de un controlador
    public Page<PizzaEntity> getAll(int page, int elements){
        Pageable pageRequest = PageRequest.of(page,elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);//la cual nos permite retornar todas las pizas por estas con listCrudRepository
    }

    public Page<PizzaEntity> getAvailable(int page, int elements,String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageRequest = PageRequest.of(page,elements, sort);
        return this.pizzaPagSortRepository.findByAvaiableTrue(pageRequest);

    }

    public PizzaEntity get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity getByname(String name){
        return this.pizzaRepository.findFirstByAvaiableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("La Pizza no existe"));
    }

    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public void delete(int idPizza){

        this.pizzaRepository.deleteById(idPizza);
    }
    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvaiableTrueAndDescriptionContainingIgnoreCase(ingredient);

    }

    public List<PizzaEntity> getCheapest(double price) {
        return this.pizzaRepository.findTop3ByAvaiableTrueAndPriceLessThanEqualOrderByPriceAsc(price);

    }
    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvaiableTrueAndDescriptionNotContainingIgnoreCase(ingredient);

    }
    public boolean exists(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }
}
