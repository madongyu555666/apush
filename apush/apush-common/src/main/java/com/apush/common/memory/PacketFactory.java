package com.apush.common.memory;

import com.apush.api.protocol.Command;
import com.apush.api.protocol.Packet;
import com.apush.api.protocol.UDPPacket;
import com.apush.tools.config.CC;

/**
 * 协议工厂
 * @author madongyu-ds
 *
 */
public interface PacketFactory {
    PacketFactory FACTORY = CC.mp.net.udpGateway() ? UDPPacket::new : Packet::new;

    static Packet get(Command command) {
        return FACTORY.create(command);
    }

    Packet create(Command command);
}