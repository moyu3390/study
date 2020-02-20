package com.nijunyang.spring.listener;

import org.springframework.context.ApplicationEvent;

/**
 * Description:
 * Created by nijunyang on 2020/2/20 22:05
 */
public class MyApplicationEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyApplicationEvent(Object source) {
        super(source);
    }
}
