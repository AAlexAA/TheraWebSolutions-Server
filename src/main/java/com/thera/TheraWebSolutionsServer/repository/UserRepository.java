package com.thera.TheraWebSolutionsServer.repository;

import com.thera.TheraWebSolutionsServer.model.User;
import com.thera.TheraWebSolutionsServer.repository.core.CoreRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CoreRepository<User, Integer> {

}
