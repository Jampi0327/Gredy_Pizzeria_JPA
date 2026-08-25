package com.Practica.JPA.service;

import com.Practica.JPA.persistence.entity.PizzaEntity;
import com.Practica.JPA.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import  org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired //se encarga de la inyeccion de dependencias de todo esos componentes
    public PizzaService (PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }
    //recupera todas las pizza de mi base de datos a traves de un controlador
    public List<PizzaEntity> getAll(){
        return this.pizzaRepository.findAll();//la cual nos permite retornar todas las pizas por estas con listCrudRepository
    }

    public List<PizzaEntity> getAvailable(){
        return this.pizzaRepository.findAllByAvaiableTrueOrderByPrice();
    }

    public PizzaEntity get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }
    public PizzaEntity getByname(String name){
        return this.pizzaRepository.findAllByAvaiableTrueAndNameIgnoreCase(name);
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
    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvaiableTrueAndDescriptionNotContainingIgnoreCase(ingredient);

    }
    public boolean exists(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }
}
