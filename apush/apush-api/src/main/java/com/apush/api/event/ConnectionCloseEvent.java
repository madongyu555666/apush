package com.apush.api.event;

import com.apush.api.connection.Connection;

/**
   * 链接关闭事件
 * @author madongyu-ds
 *
 */
public final class ConnectionCloseEvent implements Event{

	 public final Connection connection;


	    public ConnectionCloseEvent(Connection connection) {
	        this.connection = connection;
	    }
}
