package com.thera.TheraWebSolutionsServer.service.core;
import com.thera.TheraWebSolutionsServer.model.core.BaseEntity;
import com.thera.TheraWebSolutionsServer.model.core.ICreationData;
import com.thera.TheraWebSolutionsServer.model.core.ILastModifiedData;
import com.thera.TheraWebSolutionsServer.model.query.QueryCriteria;
import com.thera.TheraWebSolutionsServer.model.query.QueryOperator;
import com.thera.TheraWebSolutionsServer.model.query.QuerySpecification;
import com.thera.TheraWebSolutionsServer.repository.core.CoreRepository;
import com.thera.TheraWebSolutionsServer.service.filters.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Service
@Getter
public abstract class ServiceCoreImpl<T extends BaseEntity, ID, F extends Filter> implements ServiceCore<T, ID, F> {

    public CoreRepository<T, ID> repository;

    public ServiceCoreImpl(CoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public T add(T item) {

        if (item == null) {
            throw new NullPointerException();
        }

        return saveData(item);
    }

    @Override
    public T edit(T item) {

        if (item == null) {
            throw new NullPointerException();
        }
        return saveData(item);
    }

    @Override
    public boolean deleteById(ID id) {
        if (id == null) {
            throw new NullPointerException("Parameter Id is null.");
        }

        Optional item = repository.findById((ID) id);

        if (!item.isPresent()) {
            return false;
        }

        repository.delete((T)item.get());

        return true;
    }

    @Override
    @Transactional
    public boolean deleteById(List<ID> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new NullPointerException("Parameter Ids is null.");
        }

        boolean end = false;

        int temp = ids.size();

        int endIndex = 0;

        int startIndex = 0;


        do {
            startIndex = endIndex;

            if (temp > 2000) {
                endIndex += 2000;
                temp -= 2000;
            } else {
                endIndex = endIndex > 0
                        ? endIndex + temp
                        : temp;
                end = true;
            }

            List<ID> subList = ids.subList(startIndex, endIndex);

            List<T> items = repository.findAllById(subList);

            if (items != null && !items.isEmpty()) {
                this.repository.deleteAll(items);
            }

        } while (!end);

        return true;
    }

    @Override
    public T findById(ID id) {

        if (id == null) {
            throw new NullPointerException();
        }
        Optional<T> item = repository.findById(id);

        return item.isPresent()
                ? item.get()
                : null;
    }

    @Override
    public List<T> findById(List<ID> ids) {

        if (ids == null || ids.isEmpty()) {
            throw new NullPointerException();
        }
        List<T> items = repository.findAllById(ids);

        return items;
    }

    @Override
    public List<T> findAll(F filter) {
        List<T> result = null;
        if (filter != null && filter.getIds() != null && !filter.getIds().isEmpty()) {
            result = repository.findAllById(filter.getIds());
        } else if(filter != null){
            QuerySpecification<T> specification = createSpecificationToFindAll(filter);
            result = repository.findAll(specification);
        } else {
            result = repository.findAll();
        }
        return result;
    }

    @Override
    public Page<T> findAll(F filter, Pageable pageable) {
        Page<T> result = null;
        if (filter != null && filter.getIds() != null && !filter.getIds().isEmpty()) {
            result = (Page<T>) repository.findAllById(filter.getIds());
        } else if (filter != null) {
            QuerySpecification<T> specification = createSpecificationToFindAll(filter);
            result = repository.findAll(specification, pageable);
        } else {
            result = repository.findAll(pageable);
        }
        return result;
    }

    @Override
    public Page<T> findAll(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Override
    public List<T> findAll() {

        return repository.findAll();
    }

    @Override
    public Long count() {
        return repository.count();
    }

    protected T saveData(List<T> item) {

        updateDateTimeData(item);

        return (T) repository.saveAll(item);
    }

    protected T saveData(T item) {

        updateDateTimeData(item);

        return (T) repository.save(item);
    }

    protected QuerySpecification<T> createSpecificationToFindAll(F filter) {
        QuerySpecification<T> specification = new QuerySpecification<>();
        if (filter.getIds() != null && !filter.getIds().isEmpty()) {
            specification.AddCriteria(new QueryCriteria("id", filter.getIds(), QueryOperator.IN));
        }
        return specification;
    }

    protected void updateDateTimeData(List<T> items) {
        if (items == null || items.isEmpty()) {
            return;
        }

        items.stream().forEach(item -> {
            updateDateTimeData(item);
        });
    }

    private void updateDateTimeData(T item) {

        if (item == null) {
            return;
        }
        Date date = new Date();

        if (item instanceof ICreationData) {
            if (((ICreationData) item).getCreationDateTime() == null) {
                ((ICreationData) item).setCreationDateTime(date);
            }
        }

        if (item instanceof ILastModifiedData) {
            ((ILastModifiedData) item).setLastModifiedDateTime(date);

        }

    }

}

