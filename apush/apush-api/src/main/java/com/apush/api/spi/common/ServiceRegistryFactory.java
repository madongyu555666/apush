package com.apush.api.spi.common;

import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;
import com.apush.api.srd.ServiceRegistry;


/**
  * 记录启动信息的工厂接口，使用dubbo的spi模式，实现插播式实现
 * @author madongyu-ds
 *
 */
public interface ServiceRegistryFactory extends Factory<ServiceRegistry> {
    static ServiceRegistry create() {
        return SpiLoader.load(ServiceRegistryFactory.class).get();
    }
}
