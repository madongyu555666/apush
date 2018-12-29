package com.apush.common.message;

import com.apush.api.connection.Connection;
import com.apush.api.protocol.Command;
import com.apush.api.protocol.Packet;
import io.netty.buffer.ByteBuf;

import static com.apush.api.protocol.Command.FAST_CONNECT;

/**
 * 快速重连成功消息对象
 * @author madongyu-ds
 *
 */
public final class FastConnectOkMessage extends ByteBufMessage {
    public int heartbeat;

    public FastConnectOkMessage(Packet message, Connection connection) {
        super(message, connection);
    }

    public static FastConnectOkMessage from(BaseMessage src) {
        return new FastConnectOkMessage(src.packet.response(FAST_CONNECT), src.connection);
    }

    @Override
    public void decode(ByteBuf body) {
        heartbeat = decodeInt(body);
    }

    @Override
    public void encode(ByteBuf body) {
        encodeInt(body, heartbeat);
    }

    public FastConnectOkMessage setHeartbeat(int heartbeat) {
        this.heartbeat = heartbeat;
        return this;
    }

    @Override
    public String toString() {
        return "FastConnectOkMessage{" +
                "heartbeat=" + heartbeat +
                ", packet=" + packet +
                '}';
    }
}
