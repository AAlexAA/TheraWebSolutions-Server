package com.thera.TheraWebSolutionsServer.controller.core;

import com.thera.TheraWebSolutionsServer.model.core.BaseEntity;
import com.thera.TheraWebSolutionsServer.model.core.BaseEntityDto;
import com.thera.TheraWebSolutionsServer.service.core.Mapper;
import com.thera.TheraWebSolutionsServer.service.core.ServiceCore;
import com.thera.TheraWebSolutionsServer.service.filters.Filter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class CoreRestControllerImpl<T extends BaseEntity, Dto extends BaseEntityDto, Id, F extends Filter> implements CoreRestController<Dto, Id, F> {

    protected ServiceCore<T, Id, F> service;

    protected Mapper mapper;

    public CoreRestControllerImpl(ServiceCore service, Mapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<Dto> findAll() {
        List<T> items = service.findAll();
        List<Dto> result = mapper.toDtos(items);
        return result;
    }

    @Override
    public Dto findById(@PathVariable Id id) {
        T item = service.findById(id);
        if (item == null) {
            throw new RuntimeException("id not found - " + id);
        }
        return (Dto) mapper.toDto(item);
    }

    @Override
    public List<Dto> findQuery(@RequestBody F filter) {
        List<T> items = service.findAll(filter);
        List<Dto> result = mapper.toDtos(items);
        return result;
    }

    @Override
    public Dto add(@RequestBody Dto itemDto) {
        T item = (T) mapper.toEntity(itemDto);
        service.add(item);
        return (Dto) mapper.toDto(item);
    }

    @Override
    public Dto update(@RequestBody Dto itemDto) throws Exception {
        T item = (T) mapper.toEntity(itemDto);
        service.edit(item);
        return (Dto) mapper.toDto(item);
    }

    @Override
    public boolean deleteById(@PathVariable Id id) {

        T item = service.findById(id);
        if (item == null) {
            throw new RuntimeException("id not found - " + id);
        }
        boolean result = service.deleteById(id);
        return result;
    }

    @Override
    public boolean delete(@RequestBody Filter<Id> filter) {

        if (filter == null || filter.getIds() == null || filter.getIds().isEmpty()) {
            throw new RuntimeException("Filter by id is null - " + filter);
        }
        boolean result = service.deleteById(filter.getIds());
        return result;
    }
}
