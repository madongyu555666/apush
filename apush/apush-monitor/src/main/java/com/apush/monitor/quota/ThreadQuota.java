package com.apush.monitor.quota;

/**
 * 线程指标
 * @author madongyu-ds
 *
 */
public interface ThreadQuota extends MonitorQuota {

    int daemonThreadCount();

    int threadCount();

    long totalStartedThreadCount();

    int deadLockedThreadCount();

}
