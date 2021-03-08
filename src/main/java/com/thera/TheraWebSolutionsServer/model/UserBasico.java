package com.thera.TheraWebSolutionsServer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
public class UserBasico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false)
    Integer age;

    String email;
}
