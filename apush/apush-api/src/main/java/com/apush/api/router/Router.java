package com.apush.api.router;


/**
   * 路由接口
 * @author madongyu-ds
 *
 * @param <T>
 */
public interface Router<T> {
     /**
               * 得到路由的value	
      * @return
      */
	T getRouteValue();
    /**
             * 得到路由的类型，本地还是远程
     * @return
     */
    RouterType getRouteType();

    enum RouterType {
        LOCAL, REMOTE
    }


}
