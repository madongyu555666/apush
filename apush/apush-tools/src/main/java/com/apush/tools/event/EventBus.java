package com.apush.tools.event;

import com.google.common.eventbus.AsyncEventBus;
import com.apush.api.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

/**
 * EventBus是Google.Guava提供的消息发布-订阅类库，它实现了观察者设计模式，消息通知负责人通过EventBus去注册/注销观察者，最后由消息通知负责人给观察者发布消息。
 * 事件总线
 * @author madongyu-ds
 *
 */
public class EventBus {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventBus.class);
    private static com.google.common.eventbus.EventBus eventBus;

    public static void create(Executor executor) {
        eventBus = new AsyncEventBus(executor, (exception, context)
                -> LOGGER.error("event bus subscriber ex", exception));
    }

    public static void post(Event event) {
        eventBus.post(event);
    }

    public static void register(Object bean) {
        eventBus.register(bean);
    }

    public static void unregister(Object bean) {
        eventBus.unregister(bean);
    }

}
