package com.apush.common.router;

import com.apush.common.ServerNodes;
import com.apush.tools.config.ConfigTools;


/**
 * 远程踢人的接口
 * @author madongyu-ds
 *
 */
public interface KickRemoteMsg {
    String getUserId();

    String getDeviceId();

    String getConnId();

    int getClientType();

    String getTargetServer();

    int getTargetPort();

    default boolean isTargetMachine(String host, int port) {
        return this.getTargetPort() == port
                && this.getTargetServer().equals(host);
    }
}
