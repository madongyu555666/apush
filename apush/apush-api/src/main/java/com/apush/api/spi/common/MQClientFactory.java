package com.apush.api.spi.common;

import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;


/**
 * mq的client工厂
 * @author madongyu-ds
 *
 */
public interface MQClientFactory extends Factory<MQClient> {

    static MQClient create() {
        return SpiLoader.load(MQClientFactory.class).get();
    }
}
