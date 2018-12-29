package com.apush.api.spi.core;

import com.apush.api.common.ServerEventListener;
import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;


/**
   * 服务端监听事件接口工厂
 * @author madongyu-ds
 *
 */
public interface ServerEventListenerFactory extends Factory<ServerEventListener> {
    static ServerEventListener create() {
        return SpiLoader.load(ServerEventListenerFactory.class).get();
    }
}
