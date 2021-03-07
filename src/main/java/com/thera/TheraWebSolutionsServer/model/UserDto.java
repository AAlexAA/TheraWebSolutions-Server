package com.thera.TheraWebSolutionsServer.model;

import com.thera.TheraWebSolutionsServer.model.core.BaseEntityDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto extends BaseEntityDto<Integer> {
    private String name;

    private String email;

    private Date creationDateTime;

    private Date lastModifiedDateTime;

    private Integer createdBy;

    private Integer updatedBy;
}
