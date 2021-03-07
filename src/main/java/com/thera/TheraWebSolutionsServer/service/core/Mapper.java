package com.thera.TheraWebSolutionsServer.service.core;

import com.thera.TheraWebSolutionsServer.model.core.BaseEntity;
import com.thera.TheraWebSolutionsServer.model.core.BaseEntityDto;

public interface Mapper<T extends BaseEntity, Dto extends BaseEntityDto> extends BaseMapper<T, Dto> {

}
