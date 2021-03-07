package com.thera.TheraWebSolutionsServer.service;

import com.thera.TheraWebSolutionsServer.model.User;
import com.thera.TheraWebSolutionsServer.model.query.LogicalOperator;
import com.thera.TheraWebSolutionsServer.model.query.QueryCriteria;
import com.thera.TheraWebSolutionsServer.model.query.QueryOperator;
import com.thera.TheraWebSolutionsServer.model.query.QuerySpecification;
import com.thera.TheraWebSolutionsServer.repository.UserRepository;
import com.thera.TheraWebSolutionsServer.service.core.ServiceCoreImpl;
import com.thera.TheraWebSolutionsServer.service.filters.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceCoreImpl<User, Integer, UserFilter> implements UserService {

    @Autowired
    private UserRepository repository;


    public UserServiceImpl(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<User> findAll(UserFilter filter) {
        QuerySpecification<User> querySpecification = new QuerySpecification<>();
        if (filter != null) {
            if(filter.getName() != null){
                querySpecification.AddCriteria(new QueryCriteria("name", filter.getName(), QueryOperator.MATCH));
            }
            if(filter.getEmail() != null){
                querySpecification.AddCriteria(new QueryCriteria("email", filter.getEmail(), QueryOperator.MATCH));
            }
        }

        return repository.findAll(querySpecification);
    }
}
