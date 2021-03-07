package com.thera.TheraWebSolutionsServer.model.core;

import java.util.Date;

public interface ILastModifiedData {
    Date getLastModifiedDateTime();

    void setLastModifiedDateTime(Date lastModifiedDateTime);

    Integer getUpdatedBy();

    void setUpdatedBy(Integer modifiedBy);
}
