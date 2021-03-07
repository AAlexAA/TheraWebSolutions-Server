package com.thera.TheraWebSolutionsServer.model.core;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseEntityDto<T extends Serializable> implements IIdentifiable<T> {

    protected T id;
}