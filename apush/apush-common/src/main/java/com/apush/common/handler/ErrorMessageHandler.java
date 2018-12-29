package com.apush.common.handler;

import com.apush.api.connection.Connection;
import com.apush.api.protocol.Packet;
import com.apush.common.message.ErrorMessage;

/**
 * 错误消息处理器
 * @author madongyu-ds
 *
 */
public class ErrorMessageHandler extends BaseMessageHandler<ErrorMessage> {
    @Override
    public ErrorMessage decode(Packet packet, Connection connection) {
        return new ErrorMessage(packet, connection);
    }

    @Override
    public void handle(ErrorMessage message) {

    }
}
