package com.apush.api.srd;

/**
 * 给服务启动关闭添加监听节点
 * 
 * @author madongyu-ds
 *
 */
public interface ServiceListener {
	/**
	 * 添加服务的监听节点
	 * 
	 * @param path
	 * @param node
	 */
	void onServiceAdded(String path, ServiceNode node);

	/**
	 * 更新服务的监听节点
	 * 
	 * @param path
	 * @param node
	 */
	void onServiceUpdated(String path, ServiceNode node);

	/**
	 * 移除服务的监听节点
	 * 
	 * @param path
	 * @param node
	 */
	void onServiceRemoved(String path, ServiceNode node);
}
