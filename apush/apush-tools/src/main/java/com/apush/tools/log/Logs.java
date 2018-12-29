
package com.apush.tools.log;

import com.apush.tools.config.CC;
import com.typesafe.config.ConfigRenderOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 * @author madongyu-ds
 *
 */
public interface Logs {
    boolean logInit = init();

    static boolean init() {
        if (logInit) return true;
        System.setProperty("log.home", CC.mp.log_dir);
        System.setProperty("log.root.level", CC.mp.log_level);
        System.setProperty("logback.configurationFile", CC.mp.log_conf_path);
        LoggerFactory
                .getLogger("console")
                .info(CC.mp.cfg.root().render(ConfigRenderOptions.concise().setFormatted(true)));
        return true;
    }

    Logger Console = LoggerFactory.getLogger("console"),

    CONN = LoggerFactory.getLogger("apush.conn.log"),

    MONITOR = LoggerFactory.getLogger("apush.monitor.log"),

    PUSH = LoggerFactory.getLogger("apush.push.log"),

    HB = LoggerFactory.getLogger("apush.heartbeat.log"),

    CACHE = LoggerFactory.getLogger("apush.cache.log"),

    RSD = LoggerFactory.getLogger("apush.srd.log"),

    HTTP = LoggerFactory.getLogger("apush.http.log"),

    PROFILE = LoggerFactory.getLogger("apush.profile.log");
}
