package com.apush.api.push;

/**
 * push自定义错误类
 * @author madongyu-ds
 *
 */
public class PushException extends RuntimeException {

    public PushException(Throwable cause) {
        super(cause);
    }

    public PushException(String message) {
        super(message);
    }

    public PushException(String message, Throwable cause) {
        super(message, cause);
    }
}
