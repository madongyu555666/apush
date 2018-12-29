package com.apush.api.spi.handler;

import com.apush.api.message.MessageHandler;
import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;

/**
 * push消息处理器的工厂
 * @author madongyu-ds
 *
 */
public interface PushHandlerFactory extends Factory<MessageHandler> {
    static MessageHandler create() {
        return SpiLoader.load(PushHandlerFactory.class).get();
    }
}
