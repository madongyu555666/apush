package com.apush.api.message;

import com.apush.api.connection.Connection;
import com.apush.api.protocol.Packet;


/**
   * 消息接受者
 * @author madongyu-ds
 *
 */
public interface PacketReceiver {
	 void onReceive(Packet packet, Connection connection);

}
