package com.nijunyang.exception.exception;

/**
 * Description: 
 * Created by nijunyang on 2019/12/20 9:36
 */
public class ErrorCodeException extends RuntimeException {

    private int code;
    /**
     * 其他信息
     */
    private Object[] args;

    public ErrorCodeException(int code, Object... args) {
        this.code = code;
        this.args = args;
    }

    public int getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

}






