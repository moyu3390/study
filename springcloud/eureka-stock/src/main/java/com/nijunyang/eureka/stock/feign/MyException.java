package com.nijunyang.eureka.stock.feign;

/**
 * @author nijunyang
 * @since 2021/4/21
 */
public class MyException extends RuntimeException {
    private int code;
    private String key;
    private Object[] params;

    public MyException(int code, String key, Object... params) {
        super(key);
        this.code = code;
        this.key = key;
        this.params = params;
    }

    public int getCode() {
        return this.code;
    }

    public String getKey() {
        return this.key;
    }

    public Object[] getParams() {
        return this.params;
    }
}
