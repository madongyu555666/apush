package com.apush.api.spi.core;

import com.apush.api.connection.Cipher;
import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;

/**
 * RSA加密工厂
 * @author madongyu-ds
 *
 */
public interface RsaCipherFactory extends Factory<Cipher> {
    static Cipher create() {
        return SpiLoader.load(RsaCipherFactory.class).get();
    }
}
