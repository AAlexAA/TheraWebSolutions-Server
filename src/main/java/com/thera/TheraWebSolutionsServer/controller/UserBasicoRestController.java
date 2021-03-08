package com.thera.TheraWebSolutionsServer.controller;

import com.thera.TheraWebSolutionsServer.model.UserBasico;
import com.thera.TheraWebSolutionsServer.service.UserBasicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user-basico")
public class UserBasicoRestController {

    @Autowired
    private UserBasicoService userBasicoService;

    @PostMapping("/")
    public UserBasico add(@RequestBody UserBasico item){
        return userBasicoService.add(item);
    }

    @PutMapping("/")
    public UserBasico edit(@RequestBody UserBasico item){
        return userBasicoService.edit(item);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Integer id){
        return userBasicoService.deleteById(id);
    }

    @GetMapping("/{id}")
    public UserBasico findById(@PathVariable Integer id){
        return userBasicoService.findById(id);
    }

    @GetMapping("/")
    public List<UserBasico> findAll(){
        return userBasicoService.findAll();
    }

    @GetMapping("/find-name/{name}")
    public List<UserBasico> findAllByName(@PathVariable String name){
        return userBasicoService.findUserBasicosByName(name);
    }

    @GetMapping("/find-age/{age}")
    public List<UserBasico> findAllByAge(@PathVariable Integer age){
        return userBasicoService.findUserBasicosByAge(age);
    }

    @GetMapping("/find-email/{email}")
    public List<UserBasico> findAllByEmail(@PathVariable String email){
        return userBasicoService.findUserBasicosByEmail(email);
    }

}
