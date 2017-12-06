package com.daimengshi.ddcms.event;

import io.jboot.Jboot;
import io.jboot.event.JbootEvent;
import io.jboot.event.JbootEventListener;
import io.jboot.event.annotation.EventConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhoufeng on 2017/12/5.
 * 注册一个监听者
 */
@EventConfig(action = {"event1"})
public class MyEventListener implements JbootEventListener {
    private final static Logger logger = LoggerFactory.getLogger(MyEventListener.class);

    @Override
    public void onEvent(JbootEvent jbootEvent) {
        Object data = jbootEvent.getData();
        logger.info("get event:" + data);
        Jboot.sendEvent("event2","推送一个钩子2");
    }
}