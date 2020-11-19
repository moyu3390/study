package com.nijunyang.eureka.extension.component.redis;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 * Created by nijunyang on 2020/11/18 15:00
 *
 * @author nijunyang
 */
@Getter
@Setter
public class MessageHolder {

    public MessageHolder() {
    }

    public MessageHolder(String appName, String appId, Long sendTime) {
        this.appName = appName;
        this.appId = appId;
        this.sendTime = sendTime;
    }

    private String appName;
    private String appId;
    private Long sendTime;

}
