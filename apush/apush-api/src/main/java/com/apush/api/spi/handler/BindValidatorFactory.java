package com.apush.api.spi.handler;

import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;

/**
   * 绑定验证程序的工厂
 * @author madongyu-ds
 *
 */
public interface BindValidatorFactory extends Factory<BindValidator> {
    static BindValidator create() {
        return SpiLoader.load(BindValidatorFactory.class).get();
    }
}
