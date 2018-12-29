package com.apush.api.srd;

import com.apush.api.service.Service;

/**
 * 记录启动的服务
 * 
 * @author madongyu-ds
 *
 */
public interface ServiceRegistry extends Service {

	/**
	 * 记录
	 * 
	 * @param node
	 */
	void register(ServiceNode node);

	/**
	 * 撤销记录
	 * 
	 * @param node
	 */
	void deregister(ServiceNode node);
}
