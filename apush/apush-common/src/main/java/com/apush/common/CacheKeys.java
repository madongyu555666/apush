package com.apush.common;

/**
 * 缓存keys操作(存储到redis固定的key)
 * @author madongyu-ds
 *
 */
public final class CacheKeys {

    private static final String USER_PREFIX = "mp:ur:";//用户路由

    private static final String SESSION_PREFIX = "mp:rs:";//可复用session

    private static final String FAST_CONNECTION_DEVICE_PREFIX = "mp:fcd:";

    private static final String ONLINE_USER_LIST_KEY_PREFIX = "mp:oul:";//在线用户列表

    public static final String SESSION_AES_KEY = "mp:sa";
    public static final String SESSION_AES_SEQ_KEY = "mp:sas";
    public static final String PUSH_TASK_PREFIX = "mp:pt";

    /**
     * 用户路由+用户id
     * @param userId
     * @return
     */
    public static String getUserRouteKey(String userId) {
        return USER_PREFIX + userId;
    }
    /**
     * 可复用的session
     * @param sessionId
     * @return
     */
    public static String getSessionKey(String sessionId) {
        return SESSION_PREFIX + sessionId;
    }
    /**
     * 得到设备id
     * @param deviceId
     * @return
     */
    public static String getDeviceIdKey(String deviceId) {
        return FAST_CONNECTION_DEVICE_PREFIX + deviceId;
    }
    /**
     * //在线用户列表
     * @param publicIP
     * @return
     */
    public static String getOnlineUserListKey(String publicIP) {
        return ONLINE_USER_LIST_KEY_PREFIX + publicIP;
    }
    /**
     * 得到推送push任务的id
     * @param taskId
     * @return
     */
    public static String getPushTaskKey(String taskId) {
        return PUSH_TASK_PREFIX + taskId;
    }

}
