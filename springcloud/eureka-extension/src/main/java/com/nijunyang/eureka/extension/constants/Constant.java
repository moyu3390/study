package com.nijunyang.eureka.extension.constants;

/**
 * Description:
 * Created by nijunyang on 2020/11/18 21:09
 */
public final class Constant {

    private Constant() {
    }

    /**
     * eureka服务刷新topic
     */
    public static final String REDIS_TOPIC = "eureka_server_refresh";

    /**
     * 重试次数
     */
    public static final int RETRY_TIME = 3;
}
