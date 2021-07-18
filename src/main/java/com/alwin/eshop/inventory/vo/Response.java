package com.alwin.eshop.inventory.vo;

import lombok.Data;

@Data
public class Response {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    private String status;
    private String message;

    public Response(String status) {
        this.status = status;
    }
}
