package com.apush.tools.common;

import com.apush.tools.Utils;
import com.sun.management.HotSpotDiagnosticMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.management.*;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * jvm工具类
 * @author madongyu-ds
 *
 */
public class JVMUtil {
    private static final String HOT_SPOT_BEAN_NAME = "com.sun.management:type=HotSpotDiagnostic";

    private static final Logger LOGGER = LoggerFactory.getLogger(JVMUtil.class);

    private static HotSpotDiagnosticMXBean hotSpotMXBean;
    
    /**
     * ManagementFactory是一个为我们提供各种获取JVM信息的工厂类，使用ManagementFactory可以获取大量的运行时JVM信息，比如JVM堆的使用情况，以及GC情况，线程信息等，通过这些数据项我们可以了解正在运行的JVM的情况，以便我们可以做出相应的调整
     */
    private static ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    
    /**
     * jstack 性能调优工具
     * @param stream
     * @throws Exception
     */
    public static void jstack(OutputStream stream) throws Exception {
        PrintStream out = new PrintStream(stream);
        boolean cpuTimeEnabled = threadMXBean.isThreadCpuTimeSupported() && threadMXBean.isThreadCpuTimeEnabled();
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();

        for (Map.Entry<Thread, StackTraceElement[]> entry : map.entrySet()) {
            Thread t = entry.getKey();
            StackTraceElement[] elements = entry.getValue();

            ThreadInfo tt = threadMXBean.getThreadInfo(t.getId());
            long tid = t.getId();
            Thread.State state = t.getState();
            long cpuTimeMillis = cpuTimeEnabled ? threadMXBean.getThreadCpuTime(tid) / 1000000 : -1;
            long userTimeMillis = cpuTimeEnabled ? threadMXBean.getThreadUserTime(tid) / 1000000 : -1;

            out.printf("%s id=%d state=%s deamon=%s priority=%s cpu[total=%sms,user=%sms]", t.getName(),
                    tid, t.getState(), t.isDaemon(), t.getPriority(), cpuTimeMillis, userTimeMillis);
            final LockInfo lock = tt.getLockInfo();
            if (lock != null && state != Thread.State.BLOCKED) {
                out.printf("%n    - waiting on <0x%08x> (a %s)", lock.getIdentityHashCode(), lock.getClassName());
                out.printf("%n    - locked <0x%08x> (a %s)", lock.getIdentityHashCode(), lock.getClassName());
            } else if (lock != null && state == Thread.State.BLOCKED) {
                out.printf("%n    - waiting to lock <0x%08x> (a %s)", lock.getIdentityHashCode(),
                        lock.getClassName());
            }

            if (tt.isSuspended()) {
                out.print(" (suspended)");
            }

            if (tt.isInNative()) {
                out.print(" (running in native)");
            }

            out.println();
            if (tt.getLockOwnerName() != null) {
                out.printf("     owned by %s id=%d%n", tt.getLockOwnerName(), tt.getLockOwnerId());
            }

            final MonitorInfo[] monitors = tt.getLockedMonitors();

            for (int i = 0; i < elements.length; i++) {
                final StackTraceElement element = elements[i];
                out.printf("    at %s%n", element);
                for (int j = 1; j < monitors.length; j++) {
                    final MonitorInfo monitor = monitors[j];
                    if (monitor.getLockedStackDepth() == i) {
                        out.printf("      - locked %s%n", monitor);
                    }
                }
            }

            out.println();

            final LockInfo[] locks = tt.getLockedSynchronizers();
            if (locks.length > 0) {
                out.printf("    Locked synchronizers: count = %d%n", locks.length);
                for (LockInfo l : locks) {
                    out.printf("      - %s%n", l);
                }
                out.println();
            }
        }
    }
    /**
     * 去除dumpJstack 性能优化
     * @param jvmPath
     */
    public static void dumpJstack(final String jvmPath) {
        Utils.newThread("dump-jstack-t", (() -> {
            File path = new File(jvmPath);
            if (path.exists() || path.mkdirs()) {
                File file = new File(path, System.currentTimeMillis() + "-jstack.log");
                try (FileOutputStream out = new FileOutputStream(file)) {
                    JVMUtil.jstack(out);
                } catch (Throwable t) {
                    LOGGER.error("Dump JVM cache Error!", t);
                }
            }
        })).start();
    }

    private static HotSpotDiagnosticMXBean getHotSpotMXBean() {
        try {
            return AccessController.doPrivileged(new PrivilegedExceptionAction<HotSpotDiagnosticMXBean>() {
                public HotSpotDiagnosticMXBean run() throws Exception {
                    MBeanServer server = ManagementFactory.getPlatformMBeanServer();
                    Set<ObjectName> s = server.queryNames(new ObjectName(HOT_SPOT_BEAN_NAME), null);
                    Iterator<ObjectName> itr = s.iterator();
                    if (itr.hasNext()) {
                        ObjectName name = itr.next();
                        HotSpotDiagnosticMXBean bean = ManagementFactory.newPlatformMXBeanProxy(server,
                                name.toString(), HotSpotDiagnosticMXBean.class);
                        return bean;
                    } else {
                        return null;
                    }
                }
            });
        } catch (Exception e) {
            LOGGER.error("getHotSpotMXBean Error!", e);
            return null;
        }
    }

    private static void initHotSpotMBean() throws Exception {
        if (hotSpotMXBean == null) {
            synchronized (JVMUtil.class) {
                if (hotSpotMXBean == null) {
                    hotSpotMXBean = getHotSpotMXBean();
                }
            }
        }
    }

    public static void jMap(String fileName, boolean live) {
        File f = new File(fileName, System.currentTimeMillis() + "-jmap.log");
        String currentFileName = f.getPath();
        try {
            initHotSpotMBean();
            if (f.exists()) {
                f.delete();
            }
            hotSpotMXBean.dumpHeap(currentFileName, live);
        } catch (Exception e) {
            LOGGER.error("dumpHeap Error!" + currentFileName, e);
        }
    }

    public static void dumpJmap(final String jvmPath) {
        Utils.newThread("dump-jmap-t", () -> jMap(jvmPath, false)).start();
    }
}
