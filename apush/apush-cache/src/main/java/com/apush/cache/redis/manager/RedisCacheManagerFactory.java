package com.apush.cache.redis.manager;

import com.apush.api.spi.Spi;
import com.apush.api.spi.common.CacheManager;
import com.apush.api.spi.common.CacheManagerFactory;

@Spi(order = 1)
public final class RedisCacheManagerFactory implements CacheManagerFactory {
    @Override
    public CacheManager get() {
        return RedisManager.I;
    }
}
