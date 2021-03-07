package com.thera.TheraWebSolutionsServer.controller.core;

import com.thera.TheraWebSolutionsServer.service.filters.Filter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CoreRestController<Dto, Id, F extends Filter> {

    public List<Dto> findAll();

    public Dto findById(@PathVariable Id id);

    public List<Dto> findQuery(@RequestBody F filter);

    public Dto add(@RequestBody Dto item);

    public Dto update(@RequestBody Dto item) throws Exception;

    public boolean deleteById(@PathVariable Id id);

    public boolean delete(@RequestBody Filter<Id> filter);
}
