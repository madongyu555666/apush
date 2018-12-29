package com.apush.api.event;

import com.apush.api.router.Router;

/**
   * 路由器改变事件
 * @author madongyu-ds
 *
 */
public class RouterChangeEvent implements Event{

	public final String userId;
    public final Router<?> router;

    public RouterChangeEvent(String userId, Router<?> router) {
        this.userId = userId;
        this.router = router;
    }
}
