package com.apush.zk;

import com.apush.tools.config.CC.mp.zk;

/**
 * zk的配置中心
 * @author madongyu-ds
 *
 */
public class ZKConfig {
    public static final int ZK_MAX_RETRY = 3;
    public static final int ZK_MIN_TIME = 5000;
    public static final int ZK_MAX_TIME = 5000;
    public static final int ZK_SESSION_TIMEOUT = 5000;
    public static final int ZK_CONNECTION_TIMEOUT = 5000;
    public static final String ZK_DEFAULT_CACHE_PATH = "/";

    private String hosts;
    //消化，整理
    private String digest;
    //命令空间
    private String namespace;
    //最大重试次数
    private int maxRetries = ZK_MAX_RETRY;
    //基本睡眠时间
    private int baseSleepTimeMs = ZK_MIN_TIME;
    //最大睡眠时间
    private int maxSleepMs = ZK_MAX_TIME;
    //sesion超时时间
    private int sessionTimeout = ZK_SESSION_TIMEOUT;
    //链接超时时间
    private int connectionTimeout = ZK_CONNECTION_TIMEOUT;
    //监听路径
    private String watchPath = ZK_DEFAULT_CACHE_PATH;

    public ZKConfig(String hosts) {
        this.hosts = hosts;
    }

    public static ZKConfig build() {
        return new ZKConfig(zk.server_address)
                .setConnectionTimeout(zk.connectionTimeoutMs)
                .setDigest(zk.digest)
                .setWatchPath(zk.watch_path)
                .setMaxRetries(zk.retry.maxRetries)
                .setMaxSleepMs(zk.retry.maxSleepMs)
                .setBaseSleepTimeMs(zk.retry.baseSleepTimeMs)
                .setNamespace(zk.namespace)
                .setSessionTimeout(zk.sessionTimeoutMs)
                ;
    }

    public String getHosts() {
        return hosts;
    }

    public ZKConfig setHosts(String hosts) {
        this.hosts = hosts;
        return this;
    }

    public String getNamespace() {
        return namespace;
    }

    public ZKConfig setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public ZKConfig setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
        return this;
    }

    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public ZKConfig setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
        return this;
    }

    public int getMaxSleepMs() {
        return maxSleepMs;
    }

    public ZKConfig setMaxSleepMs(int maxSleepMs) {
        this.maxSleepMs = maxSleepMs;
        return this;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public ZKConfig setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
        return this;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public ZKConfig setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    public String getDigest() {
        return digest;
    }

    public ZKConfig setDigest(String digest) {
        this.digest = digest;
        return this;
    }

    public String getWatchPath() {
        return watchPath;
    }

    public ZKConfig setWatchPath(String watchPath) {
        this.watchPath = watchPath;
        return this;
    }

    @Override
    public String toString() {
        return "ZKConfig{" +
                "hosts='" + hosts + '\'' +
                ", digest='" + digest + '\'' +
                ", namespace='" + namespace + '\'' +
                ", maxRetries=" + maxRetries +
                ", baseSleepTimeMs=" + baseSleepTimeMs +
                ", maxSleepMs=" + maxSleepMs +
                ", sessionTimeout=" + sessionTimeout +
                ", connectionTimeout=" + connectionTimeout +
                ", watchPath='" + watchPath + '\'' +
                '}';
    }
}
