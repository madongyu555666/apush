
package com.apush.api.push;




import java.util.concurrent.FutureTask;

import com.apush.api.MPushContext;
import com.apush.api.service.Service;
import com.apush.api.spi.client.PusherFactory;


public interface PushSender extends Service {

    /**
      * 创建PushSender实例
     *
     * @return PushSender
     */
    static PushSender create() {
        return PusherFactory.create();
    }

    /**
     * 推送push消息
     *
     * @param context 推送参数
     * @return FutureTask 可用于同步调用
     */
    FutureTask<PushResult> send(PushContext context);

    default FutureTask<PushResult> send(String context, String userId, PushCallback callback) {
        return send(PushContext
                .build(context)
                .setUserId(userId)
                .setCallback(callback)
        );
    }

    default FutureTask<PushResult> send(String context, String userId, AckModel ackModel, PushCallback callback) {
        return send(PushContext
                .build(context)
                .setAckModel(ackModel)
                .setUserId(userId)
                .setCallback(callback)
        );
    }

    default void setMPushContext(MPushContext context) {
    }
}
