package com.apush.api.connection;


import io.netty.channel.Channel;

/**
   *     链接管理器
 * @author madongyu-ds
 *
 */
public interface ConnectionManager {
	    /**
	             * 通过channel得到链接
	     * @param channel
	     * @return
	     */
	    Connection get(Channel channel);
        /**
                       * 通过channel移除和关闭链接
         * @param channel
         * @return
         */
	    Connection removeAndClose(Channel channel);
        /**
                       * 添加链接
         * @param connection
         */
	    void add(Connection connection);
        /**
                       * 得到链接数
         * @return
         */
	    int getConnNum();

	    void init();

	    void destroy();

}
