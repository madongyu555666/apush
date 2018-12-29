package com.apush.api.spi.common;

import java.util.concurrent.Executor;

import com.apush.api.spi.SpiLoader;

/**
   * 执行工厂
 * @author madongyu-ds
 *
 */
public interface ExecutorFactory {
    String PUSH_CLIENT = "push-client";
    String PUSH_TASK = "push-task";
    String ACK_TIMER = "ack-timer";
    String EVENT_BUS = "event-bus";
    String MQ = "mq";

    Executor get(String name);

    static ExecutorFactory create() {
        return SpiLoader.load(ExecutorFactory.class);
    }
}
