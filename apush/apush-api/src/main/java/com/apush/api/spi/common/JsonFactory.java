
package com.apush.api.spi.common;

import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;

/**
 * json消息的工厂
 * @author madongyu-ds
 *
 */
public interface JsonFactory extends Factory<Json> {

    static Json create() {
        return SpiLoader.load(JsonFactory.class).get();
    }
}
