package com.apush.common.handler;

import com.apush.api.connection.Connection;
import com.apush.api.protocol.Packet;
import com.apush.common.message.OkMessage;

/**
 * 成功消息处理器
 * @author madongyu-ds
 *
 */
public class OkMessageHandler extends BaseMessageHandler<OkMessage> {
    @Override
    public OkMessage decode(Packet packet, Connection connection) {
        return new OkMessage(packet, connection);
    }

    @Override
    public void handle(OkMessage message) {

    }
}
