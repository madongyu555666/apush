package com.apush.zk;

import com.apush.api.spi.Spi;
import com.apush.api.spi.common.ServiceDiscoveryFactory;
import com.apush.api.srd.ServiceDiscovery;

/**
   * 发现工厂
 * @author madongyu-ds
 *
 */
@Spi(order = 1)
public final class ZKDiscoveryFactory implements ServiceDiscoveryFactory {
    @Override
    public ServiceDiscovery get() {
        return ZKServiceRegistryAndDiscovery.I;
    }
}
