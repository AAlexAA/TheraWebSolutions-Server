package com.thera.TheraWebSolutionsServer.service;

import com.thera.TheraWebSolutionsServer.model.UserBasico;

import java.util.List;

public interface UserBasicoService {

    UserBasico add(UserBasico item);

    UserBasico edit(UserBasico item);

    boolean deleteById(Integer id);

    UserBasico findById(Integer id);

    List<UserBasico> findAll();

    List<UserBasico> findUserBasicosByName(String name);

    List<UserBasico> findUserBasicosByAge(Integer age);

    List<UserBasico> findUserBasicosByEmail(String email);
}
