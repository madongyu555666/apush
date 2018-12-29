package com.apush.monitor.jmx.mxbean;

/**
 * 推送中心接口
 * @author madongyu-ds
 *
 */
public interface PushCenterMXBean {
	/**
	 * 获取任务数
	 * @return
	 */
    long getTaskNum();
}
