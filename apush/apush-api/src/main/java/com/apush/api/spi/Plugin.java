package com.apush.api.spi;

import com.apush.api.MPushContext;



/**
 * 启动停止上下文（插件）
 * @author madongyu-ds
 *
 */
public interface Plugin {
	/**
	   * 启动上下文
	 * @param context
	 */
	default void init(MPushContext context) {

    }
	/**
	   * 关闭上下文
	 * @param context
	 */
    default void destroy() {

    }
}
