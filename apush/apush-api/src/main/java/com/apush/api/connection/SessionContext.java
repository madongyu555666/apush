package com.apush.api.connection;

import com.apush.api.router.ClientClassifier;

/**
 * 缓存的上下文
 * @author madongyu-ds
 *
 */
public final class SessionContext {
	
    public String osName;
    public String osVersion;
    public String clientVersion;
    public String deviceId;
    public String userId;
    public String tags;
    public int heartbeat = 10000;// 10s
    public Cipher cipher;
    private byte clientType;

    public void changeCipher(Cipher cipher) {
        this.cipher = cipher;
    }

    public SessionContext setOsName(String osName) {
        this.osName = osName;
        return this;
    }

    public SessionContext setOsVersion(String osVersion) {
        this.osVersion = osVersion;
        return this;
    }

    public SessionContext setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
        return this;
    }

    public SessionContext setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public SessionContext setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setHeartbeat(int heartbeat) {
        this.heartbeat = heartbeat;
    }

    /**
             * 判断设备id是否为空
     * @return
     */
    public boolean handshakeOk() {
        return deviceId != null && deviceId.length() > 0;
    }
    /**
             *  获取client的类型 本地，远程
     * @return
     */
    public int getClientType() {
        if (clientType == 0) {
            clientType = (byte) ClientClassifier.I.getClientType(osName);
        }
        return clientType;
    }

    public boolean isSecurity() {
        return cipher != null;
    }

    @Override
    public String toString() {
        if (userId == null && deviceId == null) {
            return "";
        }

        return "{" +
                "osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", userId='" + userId + '\'' +
                ", tags='" + tags + '\'' +
                ", heartbeat=" + heartbeat +
                '}';
    }

}
