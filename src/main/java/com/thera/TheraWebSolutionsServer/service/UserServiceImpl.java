package com.thera.TheraWebSolutionsServer.service;

import com.thera.TheraWebSolutionsServer.model.User;
import com.thera.TheraWebSolutionsServer.repository.UserRepository;
import com.thera.TheraWebSolutionsServer.service.core.ServiceCoreImpl;
import com.thera.TheraWebSolutionsServer.service.filters.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceCoreImpl<User, Integer, UserFilter> implements UserService {

    @Autowired
    private UserRepository repository;


    public UserServiceImpl(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
