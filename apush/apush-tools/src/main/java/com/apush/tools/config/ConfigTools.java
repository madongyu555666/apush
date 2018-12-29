package com.apush.tools.config;

import com.apush.tools.Utils;
import com.apush.tools.config.CC.mp.net.public_ip_mapping;

/**
 * ConfigTools 获取linux下内外网信息
 * @author madongyu-ds
 *
 */
public final class ConfigTools {

    private ConfigTools() {
    }

    public static int getHeartbeat(int min, int max) {
        return Math.max(
                CC.mp.core.min_heartbeat,
                Math.min(max, CC.mp.core.max_heartbeat)
        );
    }

    /**
     * 获取内网IP地址
     *
     * @return 内网IP地址
     */
    public static String getLocalIp() {
        if (CC.mp.net.local_ip.length() > 0) {
            return CC.mp.net.local_ip;
        }
        return Utils.lookupLocalIp();
    }

    /**
     * 获取外网IP地址
     *
     * @return 外网IP地址
     */
    public static String getPublicIp() {

        if (CC.mp.net.public_ip.length() > 0) {
            return CC.mp.net.public_ip;
        }

        String localIp = getLocalIp();

        String remoteIp = public_ip_mapping.getString(localIp);

        if (remoteIp == null) {
            remoteIp = Utils.lookupExtranetIp();
        }

        return remoteIp == null ? localIp : remoteIp;
    }


    public static String getConnectServerRegisterIp() {
        if (CC.mp.net.connect_server_register_ip.length() > 0) {
            return CC.mp.net.connect_server_register_ip;
        }
        return getPublicIp();
    }

    public static String getGatewayServerRegisterIp() {
        if (CC.mp.net.gateway_server_register_ip.length() > 0) {
            return CC.mp.net.gateway_server_register_ip;
        }
        return getLocalIp();
    }
}
