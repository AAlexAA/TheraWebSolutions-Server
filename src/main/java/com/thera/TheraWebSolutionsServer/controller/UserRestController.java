package com.thera.TheraWebSolutionsServer.controller;

import com.thera.TheraWebSolutionsServer.controller.core.CoreRestController;
import com.thera.TheraWebSolutionsServer.controller.core.CoreRestControllerImpl;
import com.thera.TheraWebSolutionsServer.model.User;
import com.thera.TheraWebSolutionsServer.model.UserDto;
import com.thera.TheraWebSolutionsServer.service.UserMapper;
import com.thera.TheraWebSolutionsServer.service.UserService;
import com.thera.TheraWebSolutionsServer.service.filters.Filter;
import com.thera.TheraWebSolutionsServer.service.filters.UserFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/user")
public class UserRestController extends CoreRestControllerImpl<User, UserDto, Integer, UserFilter> implements CoreRestController<UserDto, Integer, UserFilter> {

    public UserRestController(UserService service, UserMapper mapper) {
        super(service, mapper);
    }

    @Override
    @GetMapping("/")
    public List<UserDto> findAll() {
        return super.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Integer id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/query")
    public List<UserDto> findQuery(@RequestBody UserFilter filter) {
        return super.findQuery(filter);
    }

    @Override
    @PostMapping("/")
    public UserDto add(@RequestBody UserDto item) {
        return super.add(item);
    }

    @Override
    @PutMapping("/")
    public UserDto update(@RequestBody UserDto item) throws Exception {
        return super.update(item);
    }

    @Override
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return super.deleteById(id);
    }

    @Override
    @PostMapping(path = "/delete")
    public boolean delete(@RequestBody Filter<Integer> filter) {

        if (filter == null || filter.getIds() == null || filter.getIds().isEmpty()) {
            throw new RuntimeException("Filter by id is null - " + filter);
        }

        List<Integer> deleteIds = filter.getIds().stream().collect(Collectors.toList());

        boolean result = service.deleteById(deleteIds);

        return result;
    }
}
