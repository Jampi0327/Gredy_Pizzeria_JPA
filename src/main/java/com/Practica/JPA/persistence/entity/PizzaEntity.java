package com.Practica.JPA.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza")
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza" , nullable = false)
    private Integer id;

    @Column(nullable = false , length = 30,unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(unique = false, columnDefinition = "Decimal(5,2)")
    private double price;

    @Column(columnDefinition = "TINYINT")
    private boolean vegetarian;

    @Column(columnDefinition = "TINYINT")
    private boolean vegan;

    @Column(columnDefinition = "TINYINT" , nullable = false)
    private boolean avaiable;

}
