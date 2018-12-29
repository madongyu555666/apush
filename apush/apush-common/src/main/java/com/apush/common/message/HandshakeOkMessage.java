package com.apush.common.message;

import com.apush.api.connection.Connection;
import com.apush.api.protocol.Command;
import com.apush.api.protocol.Packet;
import io.netty.buffer.ByteBuf;

import java.util.Arrays;

import static com.apush.api.protocol.Command.HANDSHAKE;

/**
 * 握手成功消息对象
 * @author madongyu-ds
 *
 */
public final class HandshakeOkMessage extends ByteBufMessage {
    public byte[] serverKey;
    public int heartbeat;//心跳
    public String sessionId;
    public long expireTime;//失效时间

    public HandshakeOkMessage(Packet message, Connection connection) {
        super(message, connection);
    }

    @Override
    public void decode(ByteBuf body) {
        serverKey = decodeBytes(body);
        heartbeat = decodeInt(body);
        sessionId = decodeString(body);
        expireTime = decodeLong(body);
    }

    @Override
    public void encode(ByteBuf body) {
        encodeBytes(body, serverKey);
        encodeInt(body, heartbeat);
        encodeString(body, sessionId);
        encodeLong(body, expireTime);
    }

    public static HandshakeOkMessage from(BaseMessage src) {
        return new HandshakeOkMessage(src.packet.response(HANDSHAKE), src.connection);
    }

    public HandshakeOkMessage setServerKey(byte[] serverKey) {
        this.serverKey = serverKey;
        return this;
    }

    public HandshakeOkMessage setHeartbeat(int heartbeat) {
        this.heartbeat = heartbeat;
        return this;
    }

    public HandshakeOkMessage setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public HandshakeOkMessage setExpireTime(long expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    @Override
    public String toString() {
        return "HandshakeOkMessage{" +
                "expireTime=" + expireTime +
                ", serverKey=" + Arrays.toString(serverKey) +
                ", heartbeat=" + heartbeat +
                ", sessionId='" + sessionId + '\'' +
                ", packet=" + packet +
                '}';
    }
}
