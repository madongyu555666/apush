package com.apush.zk;

import com.apush.api.spi.Spi;
import com.apush.api.spi.common.ServiceRegistryFactory;
import com.apush.api.srd.ServiceRegistry;

/**
 * zk保存工厂
 * @author madongyu-ds
 *
 */
@Spi(order = 1)
public final class ZKRegistryFactory implements ServiceRegistryFactory {
    @Override
    public ServiceRegistry get() {
        return ZKServiceRegistryAndDiscovery.I;
    }
}
