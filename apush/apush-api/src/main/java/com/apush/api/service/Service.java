package com.apush.api.service;

import java.util.concurrent.CompletableFuture;


/**
   * 启动接口
 * @author madongyu-ds
 *
 */
public interface Service {
        /**
                       * 启动并添加监听器
         * @param listener
         */
	    void start(Listener listener);
        /**
                       * 停止 并添加监听器
         * @param listener
         */
	    void stop(Listener listener);
        /**
         * jdk8新特性---通过回调的方式处理计算结果（异步）
         * @return
         */
	    CompletableFuture<Boolean> start();
	    /**
         * jdk8新特性---通过回调的方式处理计算结果（异步）
         * @return
         */
	    CompletableFuture<Boolean> stop();
	    /**
	             * 异步启动
	     * @return
	     */
	    boolean syncStart();
	    /**
	             *   异步停止
	     * @return
	     */
	    boolean syncStop();
	    /**
	             * 初始化
	     */
	    void init();
        /**
                       * 判断是否运行
         * @return
         */
	    boolean isRunning();
}
