package com.apush.zk;

/**
   * 自定义zk错误类
 * @author madongyu-ds
 *
 */
public class ZKException extends RuntimeException {

    public ZKException() {
    }

    public ZKException(String message) {
        super(message);
    }

    public ZKException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZKException(Throwable cause) {
        super(cause);
    }
}
