package com.thera.TheraWebSolutionsServer.model.core;


import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@Data
public abstract class BaseEntity<T extends Serializable> implements IIdentifiable<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity entity = (BaseEntity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }


}
