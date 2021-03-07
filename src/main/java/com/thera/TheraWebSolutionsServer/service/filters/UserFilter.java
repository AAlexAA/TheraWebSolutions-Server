package com.thera.TheraWebSolutionsServer.service.filters;

import lombok.Data;

@Data
public class UserFilter extends Filter<Integer> {
    private String name;

    private String email;
}
