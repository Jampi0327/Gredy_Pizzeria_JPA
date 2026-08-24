package com.Practica.JPA.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@IdClass(OrderitemId.class)
@Getter
@Setter
@NoArgsConstructor
public class OrdenItemEntity {

    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Id
    @Column(name = "id_item", nullable = false)
    private Integer idItem;

    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(columnDefinition = "DECIMAL(2,1)", nullable = false)
    private double quantity;

    @Column(columnDefinition = "DECIMAL(5,2)", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_order", updatable = false, insertable = false)
    private OrderEntity order;

    @OneToOne
    @JoinColumn(name = "id_pizza", referencedColumnName = "id_pizza", updatable = false, insertable = false)
    private PizzaEntity pizza;
}