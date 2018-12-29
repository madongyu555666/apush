package com.apush.common.message;

import com.apush.api.Constants;
import com.apush.api.connection.Connection;
import com.apush.api.protocol.JsonPacket;
import com.apush.api.protocol.Packet;
import io.netty.channel.ChannelFutureListener;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.apush.api.protocol.Command.PUSH;

/**
 * 推送消息对象
 * @author madongyu-ds
 *
 */
public final class PushMessage extends BaseMessage {

    public byte[] content;

    public PushMessage(Packet packet, Connection connection) {
        super(packet, connection);
    }
    /**
     * 创建
     * @param connection
     * @return
     */
    public static PushMessage build(Connection connection) {
        if (connection.getSessionContext().isSecurity()) {
            return new PushMessage(new Packet(PUSH, genSessionId()), connection);
        } else {
            return new PushMessage(new JsonPacket(PUSH, genSessionId()), connection);
        }
    }

    @Override
    public void decode(byte[] body) {
        content = body;
    }

    @Override
    public byte[] encode() {
        return content;
    }

    @Override
    public void decodeJsonBody(Map<String, Object> body) {
        String content = (String) body.get("content");
        if (content != null) {
            this.content = content.getBytes(Constants.UTF_8);
        }
    }

    @Override
    public Map<String, Object> encodeJsonBody() {
        if (content != null) {
            return Collections.singletonMap("content", new String(content, Constants.UTF_8));
        }
        return null;
    }

    public boolean autoAck() {
        return packet.hasFlag(Packet.FLAG_AUTO_ACK);
    }

    public boolean needAck() {
        return packet.hasFlag(Packet.FLAG_BIZ_ACK) || packet.hasFlag(Packet.FLAG_AUTO_ACK);
    }

    public PushMessage setContent(byte[] content) {
        this.content = content;
        return this;
    }



    @Override
    public void send(ChannelFutureListener listener) {
        super.send(listener);
        this.content = null;//释放内存
    }

    @Override
    public String toString() {
        return "PushMessage{" +
                "content='" + content.length + '\'' +
                ", packet=" + packet +
                '}';
    }
}
