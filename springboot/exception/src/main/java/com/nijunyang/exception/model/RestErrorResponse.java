package com.nijunyang.exception.model;

/**
 * Description: 
 * Created by nijunyang on 2019/12/20 9:38
 */
public class RestErrorResponse {

    private Status status;
    /**
     * 错误码
     */
    private Integer errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 其他信息
     */
    private Object[] args;

    public RestErrorResponse(Status status, Integer errorCode, String errorMsg, Object[] args) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.status = status;
        this.args = args;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Status getStatus() {
        return status;
    }

    public Object[] getArgs() {
        return args;
    }
}
