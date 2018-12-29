package com.apush.api.message;

import com.apush.api.connection.Connection;
import com.apush.api.protocol.Packet;


/**
   * 消息处理器
 * @author madongyu-ds
 *
 */
public interface MessageHandler {
	void handle(Packet packet, Connection connection);

}
