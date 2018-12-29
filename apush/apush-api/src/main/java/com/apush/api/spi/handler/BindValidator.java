package com.apush.api.spi.handler;

import com.apush.api.spi.Plugin;

/**
   *  绑定验证程序
 * @author madongyu-ds
 *
 */
public interface BindValidator extends Plugin {
	
	/**
	   *   验证
	 * @param userId
	 * @param data
	 * @return
	 */
    boolean validate(String userId, String data);
}
