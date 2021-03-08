package com.thera.TheraWebSolutionsServer.repository;

import com.thera.TheraWebSolutionsServer.model.UserBasico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBasicoRepository extends CrudRepository<UserBasico, Integer> {
    List<UserBasico> findUserBasicoByAgeEquals(Integer age);
    List<UserBasico> findUserBasicoByNameContains(String name);
    List<UserBasico> findUserBasicoByEmailContains(String email);
}
