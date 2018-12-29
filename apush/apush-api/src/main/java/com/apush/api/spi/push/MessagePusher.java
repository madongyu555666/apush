package com.apush.api.spi.push;



/**
 * 消息推送的接口
 * @author madongyu-ds
 *
 */
public interface MessagePusher {
	
    void push(IPushMessage message);
}
