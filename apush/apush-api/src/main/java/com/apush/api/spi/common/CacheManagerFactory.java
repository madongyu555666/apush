
package com.apush.api.spi.common;

import com.apush.api.spi.Factory;
import com.apush.api.spi.SpiLoader;


/**
   * 缓存管理类工厂
 * @author madongyu-ds
 *
 */
public interface CacheManagerFactory extends Factory<CacheManager> {
    static CacheManager create() {
        return SpiLoader.load(CacheManagerFactory.class).get();
    }
}
