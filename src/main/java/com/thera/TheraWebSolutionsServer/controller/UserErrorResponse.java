package com.thera.TheraWebSolutionsServer.controller;

import lombok.*;

@Data
public class UserErrorResponse {

    private Integer status;
    private String message;
    private Long timestamp;

    public UserErrorResponse(Integer status, String message, Long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
