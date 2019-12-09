package com.nijunyang.util;

/**
 * Description: cos文件系统的路径拼接
 * Created by nijunyang on 2019/12/9 10:02
 */
public final class CosPathUtils {

    public static String combinePath(String...args){
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("path is empty.");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if (i == args.length - 1) {
                break;
            }
            sb.append("/");
        }
        return sb.toString();
    }
}
