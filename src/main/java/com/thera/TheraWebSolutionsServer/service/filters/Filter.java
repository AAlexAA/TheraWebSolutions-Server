package com.thera.TheraWebSolutionsServer.service.filters;

import lombok.Data;

import java.util.List;

@Data
public class Filter<ID> {

    private ID id;

    private List<ID> ids;

}
