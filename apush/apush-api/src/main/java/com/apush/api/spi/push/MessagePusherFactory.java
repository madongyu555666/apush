package com.apush.api.spi.push;

import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;

/**
  * 消息推送的工厂接口
 * @author madongyu-ds
 *
 */
public interface MessagePusherFactory extends Factory<MessagePusher> {

    static MessagePusher create() {
        return SpiLoader.load(MessagePusherFactory.class).get();
    }
}
