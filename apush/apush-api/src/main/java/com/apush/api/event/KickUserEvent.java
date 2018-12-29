package com.apush.api.event;

/**
   * 踢掉用户的事件
 * @author madongyu-ds
 *
 */
public final class KickUserEvent implements Event{
    //用户的id
	public final String userId;
	//设备的id
    public final String deviceId;
    //从哪个服务
    public final String fromServer;

    public KickUserEvent(String userId, String deviceId, String fromServer) {
        this.userId = userId;
        this.deviceId = deviceId;
        this.fromServer = fromServer;
    }
}
