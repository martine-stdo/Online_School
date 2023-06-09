package com.iss.edu.online.common;

import lombok.Data;

@Data
public class ResultResponse<T> {

    private int code;

    private String message;

    private T data;
}
