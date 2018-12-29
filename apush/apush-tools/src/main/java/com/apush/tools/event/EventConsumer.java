
package com.apush.tools.event;

/**
 * 事件消费者
 * @author madongyu-ds
 *
 */
public abstract class EventConsumer {

    public EventConsumer() {
        EventBus.register(this);
    }

}
