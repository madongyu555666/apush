package com.apush.common.message;

import com.apush.api.connection.Connection;
import com.apush.api.protocol.Command;
import com.apush.api.protocol.Packet;
import com.apush.tools.Utils;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.apush.api.protocol.Command.HTTP_PROXY;

/**
 * http返回消息的对象
 * @author madongyu-ds
 *
 */
public final class HttpResponseMessage extends ByteBufMessage {
    public int statusCode;
    public String reasonPhrase;//原因
    public Map<String, String> headers = new HashMap<>();//头
    public byte[] body;

    public HttpResponseMessage(Packet message, Connection connection) {
        super(message, connection);
    }

    @Override
    public void decode(ByteBuf body) {
        statusCode = decodeInt(body);
        reasonPhrase = decodeString(body);
        headers = Utils.headerFromString(decodeString(body));
        this.body = decodeBytes(body);
    }

    @Override
    public void encode(ByteBuf body) {
        encodeInt(body, statusCode);
        encodeString(body, reasonPhrase);
        encodeString(body, Utils.headerToString(headers));
        encodeBytes(body, this.body);
    }

    public static HttpResponseMessage from(HttpRequestMessage src) {
        return new HttpResponseMessage(src.packet.response(HTTP_PROXY), src.connection);
    }

    public HttpResponseMessage setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public HttpResponseMessage setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
        return this;
    }

    public HttpResponseMessage addHeader(String name, String value) {
        this.headers.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return "HttpResponseMessage{" +
                "statusCode=" + statusCode +
                ", reasonPhrase='" + reasonPhrase + '\'' +
                ", headers=" + headers +
                ", body=" + (body == null ? "" : body.length) +
                '}';
    }
}
