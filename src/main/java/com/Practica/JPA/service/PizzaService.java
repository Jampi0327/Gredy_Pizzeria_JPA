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

    public PizzaEntity get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }
    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }
    public boolean exists(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }
}
