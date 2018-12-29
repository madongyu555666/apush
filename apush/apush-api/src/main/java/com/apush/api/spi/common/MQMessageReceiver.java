package com.apush.api.spi.common;


/**
 * mq接受接口
 * @author madongyu-ds
 *
 */
public interface MQMessageReceiver {
	  /**
	        * 收到方法
	   * @param topic
	   * @param message
	   */
	  void receive(String topic, Object message);
}
