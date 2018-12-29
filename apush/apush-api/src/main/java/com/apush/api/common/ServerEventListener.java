package com.apush.api.common;

import com.apush.api.event.KickUserEvent;
import com.apush.api.event.RouterChangeEvent;
import com.apush.api.event.ServerShutdownEvent;
import com.apush.api.event.ServerStartupEvent;
import com.apush.api.event.UserOfflineEvent;
import com.apush.api.event.UserOnlineEvent;

/**
   *   服务端事件的监听器
 * @author madongyu-ds
 *
 */
public interface ServerEventListener {

    /**
     * 该事件通过guava EventBus发出，实现接口的方法必须增加
     *
     * <code>@Subscribe 和 @AllowConcurrentEvents</code>注解，
     * 并在构造函数调用EventBus.register(this);
     */
    default void on(ServerStartupEvent event) {
    }

    /**
     * 该事件通过guava EventBus发出，实现接口的方法必须增加
     *
     * <code>@Subscribe 和 @AllowConcurrentEvents</code>注解，
     * 并在构造函数调用EventBus.register(this);
     */
    default void on(ServerShutdownEvent server) {
    }

    /**
     * 该事件通过guava EventBus发出，实现接口的方法必须增加
     *
     * <code>@Subscribe 和 @AllowConcurrentEvents</code>注解，
     * 并在构造函数调用EventBus.register(this);
     */
    default void on(RouterChangeEvent event) {
    }

    /**
     * 该事件通过guava EventBus发出，实现接口的方法必须增加
     *
     * <code>@Subscribe 和 @AllowConcurrentEvents</code>注解，
     * 并在构造函数调用EventBus.register(this);
     */
    default void on(KickUserEvent event) {
    }

    /**
     * 该事件通过guava EventBus发出，实现接口的方法必须增加
     *
     * <code>@Subscribe 和 @AllowConcurrentEvents</code>注解，
     * 并在构造函数调用EventBus.register(this);
     */
    default void on(UserOnlineEvent event) {
    }

    /**
     * 该事件通过guava EventBus发出，实现接口的方法必须增加
     *
     * <code>@Subscribe 和 @AllowConcurrentEvents</code>注解，
     * 并在构造函数调用EventBus.register(this);
     */
    default void on(UserOfflineEvent event) {
    }
	
	
}
