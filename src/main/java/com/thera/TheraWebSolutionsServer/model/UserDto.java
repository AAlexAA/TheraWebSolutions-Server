package com.thera.TheraWebSolutionsServer.model;

import com.thera.TheraWebSolutionsServer.model.core.BaseEntityDto;

import java.util.Date;

public class UserDto extends BaseEntityDto<Integer> {
    private String name;

    private String email;

    private Date creationDateTime;

    private Date lastModifiedDateTime;

    private Integer createdBy;

    private Integer updatedBy;
}
