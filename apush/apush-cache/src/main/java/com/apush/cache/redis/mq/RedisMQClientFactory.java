package com.apush.cache.redis.mq;

import com.apush.api.spi.Spi;
import com.apush.api.spi.common.MQClient;
import com.apush.api.spi.common.MQClientFactory;

/**
   *  得到订阅的消息后的处理的监听类
 * @author madongyu-ds
 *
 */
@Spi(order = 1)
public final class RedisMQClientFactory implements MQClientFactory {
    private ListenerDispatcher listenerDispatcher = new ListenerDispatcher();

    /**
             * 获取监听的分发类
     */
    @Override
    public MQClient get() {
        return listenerDispatcher;
    }
}
