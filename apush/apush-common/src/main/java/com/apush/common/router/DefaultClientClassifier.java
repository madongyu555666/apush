package com.apush.common.router;

import com.apush.api.router.ClientClassifier;
import com.apush.api.spi.Spi;
import com.apush.api.spi.router.ClientClassifierFactory;

/**
 * 违约的客户分发器
 * @author madongyu-ds
 *
 */
@Spi(order = 1)
public final class DefaultClientClassifier implements ClientClassifier, ClientClassifierFactory {

    @Override
    public int getClientType(String osName) {
        return ClientType.find(osName).type;
    }

    @Override
    public ClientClassifier get() {
        return this;
    }
}
