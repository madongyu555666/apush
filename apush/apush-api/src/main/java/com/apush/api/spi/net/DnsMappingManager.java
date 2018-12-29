package com.apush.api.spi.net;

import com.apush.api.service.Service;
import com.apush.api.spi.SpiLoader;

/**
 * dns管理类
 * @author madongyu-ds
 *
 */
public interface DnsMappingManager extends Service {

	
	/**
	  *  创建dns的管理类
	 * @return
	 */
    static DnsMappingManager create() {
        return SpiLoader.load(DnsMappingManager.class);
    }

    /**
             *   查找
     * @param origin
     * @return
     */
    DnsMapping lookup(String origin);
}
