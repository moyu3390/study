package com.nijunyang.exception.exception;

/**
 * Description: 
 * Created by nijunyang on 2019/12/20 9:36
 */
public class RequestException extends Exception {

    private int code;
    /**
     * 其他信息
     */
    private Object[] args;

    public RequestException(String message, int code, Object... args) {
        super(message);
        this.code = code;
        this.args = args;
    }

    public int getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

//    @Override
//    public String toString() {
//        String message = getLocalizedMessage();
//        StringBuffer sb = new StringBuffer("[");
//        for (int i = 0; i < args.length; i++) {
//            if (i <  args.length - 1) {
//                sb.append(args[i])
//                        .append(",");
//                continue;
//            }
//            sb.append(args[i]);
//        }
//        sb.append("]");
//        return "msg: "+ message + " , args: " + sb.toString();
//    }
}






