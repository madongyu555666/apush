package com.apush.api.protocol;

import java.net.InetSocketAddress;




import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

/**
   * 协议包
 * @author madongyu-ds
 *
 */
public class Packet {
	    //headerlen,请求长度
	    public static final int HEADER_LEN = 13;
        //flagcrypto 密码
	    public static final byte FLAG_CRYPTO = 1;
	    //flagcompress 压缩
	    public static final byte FLAG_COMPRESS = 2;
	    //flagbizack  ack的包（压缩）
	    public static final byte FLAG_BIZ_ACK = 4;//由客户端业务自己确认消息是否到达
	    //flagautoack 自动
	    public static final byte FLAG_AUTO_ACK = 8;////客户端收到消息后自动确认消息
	    //flagjsonbody json格式的body
	    public static final byte FLAG_JSON_BODY = 16;
        //信息的字节
	    public static final byte HB_PACKET_BYTE = -33;
	    //信息的字节数组
	    public static final byte[] HB_PACKET_BYTES = new byte[]{HB_PACKET_BYTE};
	    //心跳
	    public static final Packet HB_PACKET = new Packet(Command.HEARTBEAT);

	    public byte cmd; //命令
	    transient public short cc; //校验码 暂时没有用到
	    public byte flags; //特性，如是否加密，是否压缩等
	    public int sessionId; // 会话id。客户端生成。
	    transient public byte lrc; // 校验，纵向冗余校验。只校验head
	    transient public byte[] body;

	    public Packet(byte cmd) {
	        this.cmd = cmd;
	    }

	    public Packet(byte cmd, int sessionId) {
	        this.cmd = cmd;
	        this.sessionId = sessionId;
	    }

	    public Packet(Command cmd) {
	        this.cmd = cmd.cmd;
	    }

	    public Packet(Command cmd, int sessionId) {
	        this.cmd = cmd.cmd;
	        this.sessionId = sessionId;
	    }

	    public int getBodyLength() {
	        return body == null ? 0 : body.length;
	    }

	    public void addFlag(byte flag) {
	        this.flags |= flag;
	    }

	    public boolean hasFlag(byte flag) {
	        return (flags & flag) != 0;
	    }

	    public <T> T getBody() {
	        return (T) body;
	    }

	    public <T> void setBody(T body) {
	        this.body = (byte[]) body;
	    }

	    public short calcCheckCode() {
	        short checkCode = 0;
	        if (body != null) {
	            for (int i = 0; i < body.length; i++) {
	                checkCode += (body[i] & 0x0ff);
	            }
	        }
	        return checkCode;
	    }

	    public byte calcLrc() {
	        byte[] data = Unpooled.buffer(HEADER_LEN - 1)
	                .writeInt(getBodyLength())
	                .writeByte(cmd)
	                .writeShort(cc)
	                .writeByte(flags)
	                .writeInt(sessionId)
	                .array();
	        byte lrc = 0;
	        for (int i = 0; i < data.length; i++) {
	            lrc ^= data[i];
	        }
	        return lrc;
	    }

	    public boolean validCheckCode() {
	        return calcCheckCode() == cc;
	    }

	    public boolean validLrc() {
	        return (lrc ^ calcLrc()) == 0;
	    }

	    public InetSocketAddress sender() {
	        return null;
	    }

	    public void setRecipient(InetSocketAddress sender) {
	    }

	    public Packet response(Command command) {
	        return new Packet(command, sessionId);
	    }

	    public Object toFrame(Channel channel) {
	        return this;
	    }

	    public static Packet decodePacket(Packet packet, ByteBuf in, int bodyLength) {
	        packet.cc = in.readShort();//read cc
	        packet.flags = in.readByte();//read flags
	        packet.sessionId = in.readInt();//read sessionId
	        packet.lrc = in.readByte();//read lrc  纵向冗余校验

	        //read body
	        if (bodyLength > 0) {
	            in.readBytes(packet.body = new byte[bodyLength]);
	        }
	        return packet;
	    }

	    public static void encodePacket(Packet packet, ByteBuf out) {
	        if (packet.cmd == Command.HEARTBEAT.cmd) {
	            out.writeByte(Packet.HB_PACKET_BYTE);
	        } else {
	            out.writeInt(packet.getBodyLength());
	            out.writeByte(packet.cmd);
	            out.writeShort(packet.cc);
	            out.writeByte(packet.flags);
	            out.writeInt(packet.sessionId);
	            out.writeByte(packet.lrc);
	            if (packet.getBodyLength() > 0) {
	                out.writeBytes(packet.body);
	            }
	        }
	        packet.body = null;
	    }

	    @Override
	    public String toString() {
	        return "{" +
	                "cmd=" + cmd +
	                ", cc=" + cc +
	                ", flags=" + flags +
	                ", sessionId=" + sessionId +
	                ", lrc=" + lrc +
	                ", body=" + (body == null ? 0 : body.length) +
	                '}';
	    }

	    public static ByteBuf getHBPacket() {
	        return Unpooled.wrappedBuffer(HB_PACKET_BYTES);
	    }

}
