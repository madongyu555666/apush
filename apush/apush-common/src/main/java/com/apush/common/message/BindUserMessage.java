package com.apush.common.message;

import com.apush.api.connection.Connection;
import com.apush.api.protocol.Command;
import com.apush.api.protocol.Packet;
import io.netty.buffer.ByteBuf;

import java.util.Map;

/**
 * 捆绑用户和消息的类（保存用户和消息的对应关系）
 * @author madongyu-ds
 *
 */
public final class BindUserMessage extends ByteBufMessage {
    public String userId;
    public String tags;
    public String data;

    public BindUserMessage(Connection connection) {
        super(new Packet(Command.BIND, genSessionId()), connection);
    }

    public BindUserMessage(Packet message, Connection connection) {
        super(message, connection);
    }

    @Override
    public void decode(ByteBuf body) {
        userId = decodeString(body);
        data = decodeString(body);
        tags = decodeString(body);
    }

    @Override
    public void encode(ByteBuf body) {
        encodeString(body, userId);
        encodeString(body, data);
        encodeString(body, tags);
    }

    @Override
    public void decodeJsonBody(Map<String, Object> body) {
        userId = (String) body.get("userId");
        tags = (String) body.get("tags");
        data = (String) body.get("data");
    }

    @Override
    public String toString() {
        return "BindUserMessage{" +
                "data='" + data + '\'' +
                ", userId='" + userId + '\'' +
                ", tags='" + tags + '\'' +
                ", packet=" + packet +
                '}';
    }
}
