package com.apush.api.srd;

import java.util.List;

import com.apush.api.service.Service;

/**
 * 服务发现功能，主要是获取启动服务的信息，还有监听节点的信息
 * 
 * @author madongyu-ds
 *
 */
public interface ServiceDiscovery extends Service {

	/**
	 * 查询所有节点
	 * 
	 * @param path
	 * @return
	 */
	List<ServiceNode> lookup(String path);

	/**
	 * 订阅
	 * 
	 * @param path
	 * @param listener
	 */
	void subscribe(String path, ServiceListener listener);

	/**
	 * 取消订阅
	 * 
	 * @param path
	 * @param listener
	 */
	void unsubscribe(String path, ServiceListener listener);
}
