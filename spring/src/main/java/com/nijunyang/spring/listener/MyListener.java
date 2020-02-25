package com.nijunyang.spring.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by nijunyang on 2020/2/20 21:53
 */
@Component
public class MyListener implements ApplicationListener<MyApplicationEvent> {
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println("收到事件：" + event.toString());
    }
}
