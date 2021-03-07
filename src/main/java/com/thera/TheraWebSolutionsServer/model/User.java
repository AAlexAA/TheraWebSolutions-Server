package com.thera.TheraWebSolutionsServer.model;

import com.thera.TheraWebSolutionsServer.model.core.BaseEntity;
import com.thera.TheraWebSolutionsServer.model.core.ICreationData;
import com.thera.TheraWebSolutionsServer.model.core.ILastModifiedData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "user")
@Data
public class User extends BaseEntity<Integer> implements ICreationData, ILastModifiedData, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date creationDateTime;

    @Column(nullable = false)
    private Date lastModifiedDateTime;

    private Integer createdBy;

    private Integer updatedBy;
}