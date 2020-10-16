package cn.sanleny.configclient.sample;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义实现一个事件发布
 * @Author: sanleny
 * @Date: 2019-03-29
 * @Description: cn.sanleny.configclient.sample
 * @Version: 1.0
 */
public class MyApplicationEvent extends ApplicationEvent {
    public MyApplicationEvent(Object source) {
        super(source);
    }
}
