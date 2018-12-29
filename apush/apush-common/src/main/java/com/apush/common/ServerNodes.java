package com.apush.common;

import com.apush.api.srd.CommonServiceNode;
import com.apush.api.srd.ServiceNames;
import com.apush.api.srd.ServiceNode;
import com.apush.tools.config.CC;
import com.apush.tools.config.ConfigTools;

import static com.apush.api.srd.ServiceNames.ATTR_PUBLIC_IP;


public class ServerNodes {

	/**
	 * 创建保存节点对象
	 * @return
	 */
    public static ServiceNode cs() {
        CommonServiceNode node = new CommonServiceNode();
        node.setHost(ConfigTools.getConnectServerRegisterIp());
        node.setPort(CC.mp.net.connect_server_port);
        node.setPersistent(false);
        node.setServiceName(ServiceNames.CONN_SERVER);
        node.setAttrs(CC.mp.net.connect_server_register_attr);
        return node;
    }
    
    /**
     * 创建ws节点对象
     * @return
     */
    public static ServiceNode ws() {
        CommonServiceNode node = new CommonServiceNode();
        node.setHost(ConfigTools.getConnectServerRegisterIp());
        node.setPort(CC.mp.net.ws_server_port);
        node.setPersistent(false);
        node.setServiceName(ServiceNames.WS_SERVER);
        //node.addAttr(ATTR_PUBLIC_IP, ConfigTools.getPublicIp());
        return node;
    }
    /**
     * 创建接入层上报对象
     * @return
     */
    public static ServiceNode gs() {
        CommonServiceNode node = new CommonServiceNode();
        node.setHost(ConfigTools.getGatewayServerRegisterIp());
        node.setPort(CC.mp.net.gateway_server_port);
        node.setPersistent(false);
        node.setServiceName(ServiceNames.GATEWAY_SERVER);
        return node;
    }
}
