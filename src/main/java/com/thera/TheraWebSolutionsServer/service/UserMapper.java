package com.thera.TheraWebSolutionsServer.service;

import com.thera.TheraWebSolutionsServer.model.User;
import com.thera.TheraWebSolutionsServer.model.UserDto;
import com.thera.TheraWebSolutionsServer.service.core.MapperImpl;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends MapperImpl<User, UserDto> {

    public UserMapper() { super(User.class, UserDto.class);}

    @Override
    public void configure() {
        PropertyMap<User, UserDto> mapping = new PropertyMap<User, UserDto>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
            }
        };
        modelMapper.addMappings(mapping);
    }
}
