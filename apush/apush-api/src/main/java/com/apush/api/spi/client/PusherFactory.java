package com.apush.api.spi.client;

import com.apush.api.push.PushSender;
import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;

/**
 * push工厂
 * @author madongyu-ds
 *
 */
public interface PusherFactory extends Factory<PushSender> {
    static PushSender create() {
        return SpiLoader.load(PusherFactory.class).get();
    }
}
