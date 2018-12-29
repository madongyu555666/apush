package com.apush.api.spi.common;

import com.apush.api.spi.Plugin;


/**
 * mq客户端，并继承上下文的开启类
 * @author madongyu-ds
 *
 */
public interface MQClient  extends Plugin{

	/**
	   *  订阅
	 * @param topic
	 * @param receiver
	 */
	 void subscribe(String topic, MQMessageReceiver receiver);
	 /**
	     * 推送
	  * @param topic
	  * @param message
	  */
	 void publish(String topic, Object message);
}
