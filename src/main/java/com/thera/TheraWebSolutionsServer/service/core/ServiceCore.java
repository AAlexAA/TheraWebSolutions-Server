package com.thera.TheraWebSolutionsServer.service.core;

import com.thera.TheraWebSolutionsServer.model.core.BaseEntity;
import com.thera.TheraWebSolutionsServer.repository.core.CoreRepository;
import com.thera.TheraWebSolutionsServer.service.filters.Filter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceCore<T extends BaseEntity, ID, F extends Filter> {

    Logger log = LogManager.getLogger();

    CoreRepository<T, ID> getRepository();

    T add(T item);

    T edit(T item);

    boolean deleteById(ID id);

    boolean deleteById(List<ID> ids);

    T findById(ID id);

    List<T> findById(List<ID> ids);

    List<T> findAll(F filter);

    Page<T> findAll(F filter, Pageable pageable);

    Page<T> findAll(Pageable pageable);

    List<T> findAll();

    Long count();
}
