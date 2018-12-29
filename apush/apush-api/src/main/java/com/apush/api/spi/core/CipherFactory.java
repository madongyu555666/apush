package com.apush.api.spi.core;

import com.apush.api.connection.Cipher;
import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;

 /**
     * 加密工厂接口
  * @author madongyu-ds
  *
  */
public interface CipherFactory extends Factory<Cipher> {
    static Cipher create() {
        return SpiLoader.load(CipherFactory.class).get();
    }
}
