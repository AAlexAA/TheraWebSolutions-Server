package com.thera.TheraWebSolutionsServer.model.core;

import java.util.Date;

public interface ICreationData {
    Date getCreationDateTime();

    void setCreationDateTime(Date creationDateTime);

    Integer getCreatedBy();

    void setCreatedBy(Integer createdBy);
}
