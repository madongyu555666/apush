package com.apush.api.common;

import java.util.concurrent.Executor;

/**
 *   监控的接口
 * @author madongyu-ds
 *
 */
public interface Monitor {
     /**
                * 使用单线程监控
      * @param name
      * @param thread
      */
	 void monitor(String name, Thread thread);

	 /**
	     * 使用线程池创建线程监控
	  * @param name
	  * @param executor
	  */
	 void monitor(String name, Executor executor);
}
