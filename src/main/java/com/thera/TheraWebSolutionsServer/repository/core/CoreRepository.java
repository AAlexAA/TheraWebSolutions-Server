package com.thera.TheraWebSolutionsServer.repository.core;

import com.thera.TheraWebSolutionsServer.model.core.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CoreRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {
    List<T> findAll(Specification specification);

    Page<T> findAll(Specification specification, Pageable pageable);
}
