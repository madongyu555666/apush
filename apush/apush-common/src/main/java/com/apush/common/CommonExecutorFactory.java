package com.apush.common;

import com.apush.api.spi.common.ExecutorFactory;
import com.apush.tools.config.CC;
import com.apush.tools.log.Logs;
import com.apush.tools.thread.NamedPoolThreadFactory;
import com.apush.tools.thread.pool.DefaultExecutor;
import com.apush.tools.thread.pool.DumpThreadRejectedHandler;
import com.apush.tools.thread.pool.ThreadPoolConfig;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.apush.tools.config.CC.mp.thread.pool.ack_timer;
import static com.apush.tools.config.CC.mp.thread.pool.push_client;
import static com.apush.tools.thread.ThreadNames.T_ARK_REQ_TIMER;
import static com.apush.tools.thread.ThreadNames.T_EVENT_BUS;
import static com.apush.tools.thread.ThreadNames.T_PUSH_CLIENT_TIMER;

/**
 * 公共的连接池工厂
 * @author madongyu-ds
 *
 */
public class CommonExecutorFactory implements ExecutorFactory {
    protected Executor get(ThreadPoolConfig config) {
        String name = config.getName();
        int corePoolSize = config.getCorePoolSize();
        int maxPoolSize = config.getMaxPoolSize();
        int keepAliveSeconds = config.getKeepAliveSeconds();
        BlockingQueue<Runnable> queue = config.getQueue();

        return new DefaultExecutor(corePoolSize
                , maxPoolSize
                , keepAliveSeconds
                , TimeUnit.SECONDS
                , queue
                , new NamedPoolThreadFactory(name)
                , new DumpThreadRejectedHandler(config));
    }

    @Override
    public Executor get(String name) {
        final ThreadPoolConfig config;
        switch (name) {
            case EVENT_BUS:
                config = ThreadPoolConfig
                        .build(T_EVENT_BUS)
                        .setCorePoolSize(CC.mp.thread.pool.event_bus.min)
                        .setMaxPoolSize(CC.mp.thread.pool.event_bus.max)
                        .setKeepAliveSeconds(TimeUnit.SECONDS.toSeconds(10))
                        .setQueueCapacity(CC.mp.thread.pool.event_bus.queue_size)
                        .setRejectedPolicy(ThreadPoolConfig.REJECTED_POLICY_CALLER_RUNS);
                break;
            case PUSH_CLIENT: {
                ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(push_client
                        , new NamedPoolThreadFactory(T_PUSH_CLIENT_TIMER), (r, e) -> r.run() // run caller thread
                );
                executor.setRemoveOnCancelPolicy(true);
                return executor;
            }
            case ACK_TIMER: {
                ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(ack_timer,
                        new NamedPoolThreadFactory(T_ARK_REQ_TIMER),
                        (r, e) -> Logs.PUSH.error("one ack context was rejected, context=" + r)
                );
                executor.setRemoveOnCancelPolicy(true);
                return executor;
            }
            default:
                throw new IllegalArgumentException("no executor for " + name);
        }

        return get(config);
    }
}
