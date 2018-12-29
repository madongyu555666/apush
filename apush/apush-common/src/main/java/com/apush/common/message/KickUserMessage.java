package com.apush.common.message;

import com.apush.api.connection.Connection;
import com.apush.api.protocol.JsonPacket;
import com.apush.api.protocol.Packet;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.apush.api.protocol.Command.KICK;
import static com.apush.api.protocol.Command.PUSH;

/**
 * 踢人消息对象
 * @author madongyu-ds
 *
 */
public class KickUserMessage extends ByteBufMessage {
    public String deviceId;
    public String userId;

    public KickUserMessage(Packet message, Connection connection) {
        super(message, connection);
    }

    public static KickUserMessage build(Connection connection) {
        if (connection.getSessionContext().isSecurity()) {
            return new KickUserMessage(new Packet(KICK), connection);
        } else {
            return new KickUserMessage(new JsonPacket(KICK), connection);
        }
    }

    @Override
    public void decode(ByteBuf body) {
        deviceId = decodeString(body);
        userId = decodeString(body);
    }

    @Override
    public void encode(ByteBuf body) {
        encodeString(body, deviceId);
        encodeString(body, userId);
    }

    @Override
    protected Map<String, Object> encodeJsonBody() {
        Map<String, Object> body = new HashMap<>(2);
        body.put("deviceId", deviceId);
        body.put("userId", userId);
        return body;
    }

    @Override
    public String toString() {
        return "KickUserMessage{" +
                "deviceId='" + deviceId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
