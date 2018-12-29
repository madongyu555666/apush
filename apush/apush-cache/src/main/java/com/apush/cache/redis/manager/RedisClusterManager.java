package com.apush.cache.redis.manager;

import com.apush.cache.redis.RedisServer;

import java.util.List;

/**
 * redis cluster管理接口
 * @author madongyu-ds
 *
 */
public interface RedisClusterManager {

    void init();

    List<RedisServer> getServers();
}
