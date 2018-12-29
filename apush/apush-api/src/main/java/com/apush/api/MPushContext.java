package com.apush.api;

import com.apush.api.common.Monitor;
import com.apush.api.spi.common.CacheManager;
import com.apush.api.spi.common.MQClient;
import com.apush.api.srd.ServiceDiscovery;
import com.apush.api.srd.ServiceRegistry;

/**
 * push推送的上下文
 * @author madongyu-ds
 *
 */
public interface MPushContext {

	  Monitor getMonitor();

	    ServiceDiscovery getDiscovery();

	    ServiceRegistry getRegistry();

	    CacheManager getCacheManager();

	    MQClient getMQClient();
}
