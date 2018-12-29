package com.apush.api.event;

import com.apush.api.connection.Connection;


/**
   * 开启链接事件
 * @author madongyu-ds
 *
 */
public final class ConnectionConnectEvent implements Event {
    public final Connection connection;

    public ConnectionConnectEvent(Connection connection) {
        this.connection = connection;
    }
}