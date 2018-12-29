package com.apush.api.spi.push;

import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;

/**
   * 推送消息监听工厂接口（spi）
 * @author madongyu-ds
 *
 * @param <M>
 */
public interface PushListenerFactory<M extends IPushMessage> extends Factory<PushListener<M>> {

    @SuppressWarnings("unchecked")
    static <M extends IPushMessage> PushListener<M> create() {
        return (PushListener<M>) SpiLoader.load(PushListenerFactory.class).get();
    }
}
