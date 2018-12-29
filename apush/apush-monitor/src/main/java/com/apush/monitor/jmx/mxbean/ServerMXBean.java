package com.apush.monitor.jmx.mxbean;

/**
 * 业务接口
 * @author madongyu-ds
 *
 */
public interface ServerMXBean {
    /**
     * @返回服务器套接字端口号
     */
    String getClientPort();

    /**
     * @返回服务器版本
     */
    String getVersion();

    /**
     * @服务器启动的返回时间
     */
    String getStartTime();

    /**
     * @return min请求延迟
     */
    long getMinRequestLatency();

    /**
     * @ms中的返回平均请求等待时间
     */
    long getAvgRequestLatency();

    /**
     * ms中的@return max请求等待时间
     */
    long getMaxRequestLatency();

    /**
     * @到目前为止收到的分组的返回数
     */
    long getPacketsReceived();

    /**
     * @return number of packets sent so far
     */
    long getPacketsSent();

    /**
     * @return number of outstanding requests.
     */
    long getOutstandingRequests();

    /**
     * Current TickTime of server in milliseconds
     */
    int getTickTime();

    /**
     * Set TickTime of server in milliseconds
     */
    void setTickTime(int tickTime);

    /**
     * Current maxClientCnxns allowed from a particular host
     */
    int getMaxClientCnxnsPerHost();

    /**
     * Set maxClientCnxns allowed from a particular host
     */
    void setMaxClientCnxnsPerHost(int max);

    /**
     * Current minSessionTimeout of the server in milliseconds
     */
    int getMinSessionTimeout();

    /**
     * Set minSessionTimeout of server in milliseconds
     */
    void setMinSessionTimeout(int min);

    /**
     * Current maxSessionTimeout of the server in milliseconds
     */
    int getMaxSessionTimeout();

    /**
     * Set maxSessionTimeout of server in milliseconds
     */
    void setMaxSessionTimeout(int max);

    /**
     * Reset packet and latency statistics
     */
    void resetStatistics();

    /**
     * Reset min/avg/max latency statistics
     */
    void resetLatency();

    /**
     * Reset max latency statistics only.
     */
    void resetMaxLatency();

    /**
     * @return number of alive client connections
     */
    long getNumAliveConnections();
}
