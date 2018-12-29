package com.apush.api.event;

import com.apush.api.connection.Connection;

/**
 * 握手事件
 * @author madongyu-ds
 *
 */
public final class HandshakeEvent implements Event{
	    public final Connection connection;
	    public final int heartbeat;

	    public HandshakeEvent(Connection connection, int heartbeat) {
	        this.connection = connection;
	        this.heartbeat = heartbeat;
	    }
}
