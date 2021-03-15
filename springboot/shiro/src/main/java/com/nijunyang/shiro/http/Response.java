package com.nijunyang.shiro.http;

import lombok.Data;

@Data
public class Response<T> {

    private T data;

    private int code;

    private String msg;
}