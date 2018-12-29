package com.apush.common.security;

import com.apush.api.connection.Cipher;
import com.apush.api.spi.Spi;
import com.apush.api.spi.core.RsaCipherFactory;

/**
 * 违约的RSA加密工厂
 * @author madongyu-ds
 *
 */
@Spi
public class DefaultRsaCipherFactory implements RsaCipherFactory {
    private static final RsaCipher RSA_CIPHER = RsaCipher.create();

    @Override
    public Cipher get() {
        return RSA_CIPHER;
    }
}
