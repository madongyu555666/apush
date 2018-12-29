package com.apush.api.srd;


/**
   * 监听服务的节点，并返回监听节点的信息
 * @author madongyu-ds
 *
 */
public interface ServiceNode {

	    String serviceName();

	    String nodeId();

	    String getHost();

	    int getPort();

	    default <T> T getAttr(String name) {
	        return null;
	    }

	    default boolean isPersistent() {
	        return false;
	    }

	    default String hostAndPort() {
	        return getHost() + ":" + getPort();
	    }

	    default String nodePath() {
	        return serviceName() + '/' + nodeId();
	    }
}
