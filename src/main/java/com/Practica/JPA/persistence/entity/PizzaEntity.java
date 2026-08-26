package com.Practica.JPA.persistence.entity;

import com.Practica.JPA.persistence.audit.AuditableEntity;
import com.Practica.JPA.persistence.audit.auditPizzaListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Table(name = "pizza")
@EntityListeners({AuditingEntityListener.class, auditPizzaListener.class})
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity implements Serializable {
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

    @Override
    public String toString() {
        return "PizzaEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", vegetarian=" + vegetarian +
                ", vegan=" + vegan +
                ", avaiable=" + avaiable +
                '}';
    }
}
