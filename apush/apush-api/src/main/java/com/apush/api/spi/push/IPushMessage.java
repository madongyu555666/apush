package com.apush.api.spi.push;

import com.apush.api.common.Condition;

/**
   * 推送消息接口方法
 * @author madongyu-ds
 *
 */
public interface IPushMessage {

	/**
	 * 是否广播
	 * @return
	 */
    boolean isBroadcast();

    String getUserId();

    int getClientType();

    byte[] getContent();

    boolean isNeedAck();

    byte getFlags();

    int getTimeoutMills();

    default String getTaskId() {
        return null;
    }

    default Condition getCondition() {
        return null;
    }

    default void finalized() {

    }

}
