package com.thera.TheraWebSolutionsServer.service.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface BaseMapper<T, Dto> {
    void configure();

    default Dto toDto(T item) {
        if (item == null) {
            return null;
        }
        return mapToDto(item);
    }

    default T toEntity(Dto item) {
        if (item == null) {
            return null;
        }
        return mapToEntity(item);
    }

    default List<Dto> toDtos(List<T> items) {
        if (items == null || items.isEmpty()) {
            return new ArrayList<>();
        }

        return items.stream().map(t -> (Dto) toDto(t)).collect(Collectors.toList());
    }


    default List<T> toEntities(List<Dto> items) {
        if (items == null || items.isEmpty()) {
            return new ArrayList<>();
        }

        return items.stream().map(t -> (T) toEntity(t)).collect(Collectors.toList());
    }

    Dto mapToDto(T item);

    T mapToEntity(Dto item);
}
