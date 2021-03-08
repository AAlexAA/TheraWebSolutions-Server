package com.thera.TheraWebSolutionsServer.service;

import com.thera.TheraWebSolutionsServer.model.UserBasico;
import com.thera.TheraWebSolutionsServer.repository.UserBasicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBasicoServiceImpl implements UserBasicoService {

    @Autowired
    private UserBasicoRepository userBasicoRepository;

    @Override
    public UserBasico add(UserBasico item) {
        return userBasicoRepository.save(item);
    }

    @Override
    public UserBasico edit(UserBasico item) {
        return userBasicoRepository.save(item);
    }

    @Override
    public boolean deleteById(Integer id) {
        userBasicoRepository.deleteById(id);
        return true;
    }

    @Override
    public UserBasico findById(Integer id) {
        Optional<UserBasico> user = userBasicoRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public List<UserBasico> findAll() {
        return (List<UserBasico>)(userBasicoRepository.findAll());
    }

    @Override
    public List<UserBasico> findUserBasicosByName(String name) {
        return userBasicoRepository.findUserBasicoByNameContains(name);
    }

    @Override
    public List<UserBasico> findUserBasicosByAge(Integer age) {
        return userBasicoRepository.findUserBasicoByAgeEquals(age);
    }

    @Override
    public List<UserBasico> findUserBasicosByEmail(String email) {
        return userBasicoRepository.findUserBasicoByEmailContains(email);
    }
}
