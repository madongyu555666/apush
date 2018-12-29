package com.apush.cache.redis.mq;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.apush.api.MPushContext;
import com.apush.api.spi.common.ExecutorFactory;
import com.apush.api.spi.common.MQClient;
import com.apush.api.spi.common.MQMessageReceiver;
import com.apush.cache.redis.manager.RedisManager;
import com.apush.monitor.service.MonitorService;
import com.apush.monitor.service.ThreadPoolManager;
import com.apush.tools.log.Logs;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
   * 监听的分发器
 * @author madongyu-ds
 *
 */
public final class ListenerDispatcher implements MQClient {
	//订阅，订阅一个频道的所有接受者
    private final Map<String, List<MQMessageReceiver>> subscribes = Maps.newTreeMap();

    private final Subscriber subscriber;

    private Executor executor;

    /**
             * 初始化线程池
     */
    @Override
    public void init(MPushContext context) {
        executor = ((MonitorService) context.getMonitor()).getThreadPoolManager().getRedisExecutor();
    }

    public ListenerDispatcher() {
        this.subscriber = new Subscriber(this);
    }
  
    public void onMessage(String channel, String message) {
    	//通过通道获取所有的接受者
        List<MQMessageReceiver> listeners = subscribes.get(channel);
        if (listeners == null) {
            Logs.CACHE.info("cannot find listener:{}, {}", channel, message);
            return;
        }
        //遍历接受者，通过线程池去并行接收消息
        for (MQMessageReceiver listener : listeners) {
            executor.execute(() -> listener.receive(channel, message));
        }
    }

    public void subscribe(String channel, MQMessageReceiver listener) {
        subscribes.computeIfAbsent(channel, k -> Lists.newArrayList()).add(listener);
        RedisManager.I.subscribe(subscriber, channel);
    }

    @Override
    public void publish(String topic, Object message) {
        RedisManager.I.publish(topic, message);
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }
}
