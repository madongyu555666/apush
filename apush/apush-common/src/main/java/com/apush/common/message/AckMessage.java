package com.apush.common.message;

import com.apush.api.connection.Connection;
import com.apush.api.protocol.Command;
import com.apush.api.protocol.Packet;
import io.netty.buffer.ByteBuf;

/**
 * ack包，也就是应答包对象
 * @author madongyu-ds
 *
 */
public final class AckMessage extends BaseMessage {


    public AckMessage(Packet packet, Connection connection) {
        super(packet, connection);
    }

    @Override
    public void decode(byte[] body) {

    }

    @Override
    public byte[] encode() {
        return null;
    }


    public static AckMessage from(BaseMessage src) {
        return new AckMessage(new Packet(Command.ACK, src.getSessionId()), src.connection);
    }

    @Override
    public String toString() {
        return "AckMessage{" +
                "packet=" + packet +
                '}';
    }
}
