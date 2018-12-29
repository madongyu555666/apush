
package com.apush.tools.thread.pool;

import com.apush.tools.Utils;
import com.apush.tools.common.JVMUtil;
import com.apush.tools.config.CC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import static com.apush.tools.thread.pool.ThreadPoolConfig.REJECTED_POLICY_ABORT;
import static com.apush.tools.thread.pool.ThreadPoolConfig.REJECTED_POLICY_CALLER_RUNS;



/**
 * 自定义线程池拒绝策略
 * @author madongyu-ds
 *
 */
public final class DumpThreadRejectedHandler implements RejectedExecutionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(DumpThreadRejectedHandler.class);

    private volatile boolean dumping = false;

    private static final String DUMP_DIR = CC.mp.monitor.dump_dir;

    private final ThreadPoolConfig poolConfig;

    private final int rejectedPolicy;

    public DumpThreadRejectedHandler(ThreadPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
        this.rejectedPolicy = poolConfig.getRejectedPolicy();
    }

    /**
     * 自定义策略方法
     */
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        LOGGER.warn("one task rejected, poolConfig={}, poolInfo={}", poolConfig, Utils.getPoolInfo(e));
        if (!dumping) {
            dumping = true;
            dumpJVMInfo();
        }

        if (rejectedPolicy == REJECTED_POLICY_ABORT) {
            throw new RejectedExecutionException("one task rejected, pool=" + poolConfig.getName());
        } else if (rejectedPolicy == REJECTED_POLICY_CALLER_RUNS) {
            if (!e.isShutdown()) {
                r.run();
            }
        }
    }

    private void dumpJVMInfo() {
        LOGGER.info("start dump jvm info");
        JVMUtil.dumpJstack(DUMP_DIR + "/" + poolConfig.getName());
        LOGGER.info("end dump jvm info");
    }
}

