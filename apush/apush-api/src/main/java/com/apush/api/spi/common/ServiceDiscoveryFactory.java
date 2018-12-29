package com.apush.api.spi.common;

import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;
import com.apush.api.srd.ServiceDiscovery;


/**
   * 服务发现功能，主要是获取启动服务的信息，还有监听节点的信息工厂（dubbo的spi）
 * @author madongyu-ds
 *
 */
public interface ServiceDiscoveryFactory extends Factory<ServiceDiscovery> {
    static ServiceDiscovery create() {
        return SpiLoader.load(ServiceDiscoveryFactory.class).get();
    }
}
