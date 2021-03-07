package com.thera.TheraWebSolutionsServer.service.core;

import com.thera.TheraWebSolutionsServer.model.core.BaseEntity;
import com.thera.TheraWebSolutionsServer.model.core.BaseEntityDto;

import org.modelmapper.ModelMapper;

public abstract class MapperImpl<T extends BaseEntity, Dto extends BaseEntityDto> implements Mapper<T, Dto> {

    protected ModelMapper modelMapper;

    protected Class<T> entityClass;

    protected Class<Dto> dtoClass;

    public MapperImpl(Class<T> entityClass, Class<Dto> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.modelMapper = new ModelMapper();
        this.configure();
    }

    @Override
    public Dto mapToDto(T item) {
        return modelMapper.map(item, this.dtoClass);
    }

    @Override
    public T mapToEntity(Dto item) {
        return modelMapper.map(item, this.entityClass);
    }
}

