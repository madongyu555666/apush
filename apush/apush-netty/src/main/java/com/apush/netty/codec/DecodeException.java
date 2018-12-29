package com.apush.netty.codec;

/**
 * 自定义编解码错误类
 * @author madongyu-ds
 *
 */
public class DecodeException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DecodeException(String message) {
        super(message);
    }
}
