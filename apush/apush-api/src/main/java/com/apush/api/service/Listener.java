package com.apush.api.service;


/**
 * 监听成功失败的监听器
 * @author madongyu-ds
 *
 */
public interface Listener {
     /**
               *  成功
      * @param args
      */
	 void onSuccess(Object... args);
	 /**
	     * 失败
	  * @param cause
	  */
	 void onFailure(Throwable cause);
}
