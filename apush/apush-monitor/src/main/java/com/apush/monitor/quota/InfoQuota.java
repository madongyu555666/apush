package com.apush.monitor.quota;
/**
 * 信息指标
 * @author madongyu-ds
 *
 */
public interface InfoQuota extends MonitorQuota {

	/**
	 * 进程标识符
	 * @return
	 */
    String pid();
    /**
     * 负载
     * @return
     */
    double load();
}
