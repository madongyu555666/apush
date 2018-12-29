package com.apush.cache.redis.mq;

import com.apush.tools.Jsons;
import com.apush.tools.log.Logs;
import redis.clients.jedis.JedisPubSub;

/**
  *消息监听
 * @author madongyu-ds
 *
 */
public final class Subscriber extends JedisPubSub {
    private final ListenerDispatcher listenerDispatcher;

    public Subscriber(ListenerDispatcher listenerDispatcher) {
        this.listenerDispatcher = listenerDispatcher;
    }

    /**
             * 取得订阅的消息后的处理,并添加监听
     */
    @Override
    public void onMessage(String channel, String message) {
        Logs.CACHE.info("onMessage:{},{}", channel, message);
        //添加监听
        listenerDispatcher.onMessage(channel, message);
        super.onMessage(channel, message);
    }
    /**
             * 取得按表达式的方式订阅的消息后的处理
     */
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        Logs.CACHE.info("onPMessage:{},{},{}", pattern, channel, message);
        super.onPMessage(pattern, channel, message);
    }

    /**
             * 初始化按表达式的方式订阅时候的处理
     */
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        Logs.CACHE.info("onPSubscribe:{},{}", pattern, subscribedChannels);
        super.onPSubscribe(pattern, subscribedChannels);
    }

    /**
             * 取消按表达式的方式订阅时候的处理
     */
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        Logs.CACHE.info("onPUnsubscribe:{},{}", pattern, subscribedChannels);
        super.onPUnsubscribe(pattern, subscribedChannels);
    }

    /**
             * 初始化订阅时候的处理
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        Logs.CACHE.info("onSubscribe:{},{}", channel, subscribedChannels);
        super.onSubscribe(channel, subscribedChannels);
    }
    /**
             * 取消订阅时候的处理
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        Logs.CACHE.info("onUnsubscribe:{},{}", channel, subscribedChannels);
        super.onUnsubscribe(channel, subscribedChannels);
    }


    @Override
    public void unsubscribe() {
        Logs.CACHE.info("unsubscribe");
        super.unsubscribe();
    }

    @Override
    public void unsubscribe(String... channels) {
        Logs.CACHE.info("unsubscribe:{}", Jsons.toJson(channels));
        super.unsubscribe(channels);
    }

}
