package com.Practica.JPA.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderitemId implements Serializable {

    private Integer idOrder;
    private Integer idItem;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderitemId that = (OrderitemId) o;
        return Objects.equals(idOrder, that.idOrder) && Objects.equals(idItem, that.idItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idItem);
    }
}
