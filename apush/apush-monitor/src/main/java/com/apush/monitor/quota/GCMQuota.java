package com.apush.monitor.quota;


/**
 * GC垃圾回收的方法
 * @author madongyu-ds
 *
 */
public interface GCMQuota extends MonitorQuota {

    long yongGcCollectionCount();

    long yongGcCollectionTime();

    long fullGcCollectionCount();

    long fullGcCollectionTime();

    long spanYongGcCollectionCount();

    long spanYongGcCollectionTime();

    long spanFullGcCollectionCount();

    long spanFullGcCollectionTime();

}
