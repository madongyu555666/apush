package com.apush.cache.redis;

/**
   * 自定义redis错误类
 * @author madongyu-ds
 *
 */
public class RedisException extends RuntimeException {

    public RedisException() {
    }

    public RedisException(Throwable cause) {
        super(cause);
    }

    public RedisException(String message) {
        super(message);
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }
}
