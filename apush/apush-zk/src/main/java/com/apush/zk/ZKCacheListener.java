package com.apush.zk;

import com.google.common.base.Strings;
import com.apush.api.srd.CommonServiceNode;
import com.apush.api.srd.ServiceListener;
import com.apush.tools.Jsons;
import com.apush.tools.log.Logs;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;

/**
 * zk缓存监听
 * @author madongyu-ds
 *
 */
public final class ZKCacheListener implements TreeCacheListener {//TreeCacheListener 可以监听到节点变化的接口

    private final String watchPath;

    private final ServiceListener listener;

    public ZKCacheListener(String watchPath, ServiceListener listener) {
        this.watchPath = watchPath;
        this.listener = listener;
    }

    @Override
    public void childEvent(CuratorFramework curator, TreeCacheEvent event) throws Exception {
        ChildData data = event.getData();
        if (data == null) return;
        String dataPath = data.getPath();
        if (Strings.isNullOrEmpty(dataPath)) return;
        if (dataPath.startsWith(watchPath)) {
            switch (event.getType()) {
                //添加服务的监听节点
                case NODE_ADDED:
                    listener.onServiceAdded(dataPath, Jsons.fromJson(data.getData(), CommonServiceNode.class));
                    break;
                //移除服务的监听节点
                case NODE_REMOVED:
                    listener.onServiceRemoved(dataPath, Jsons.fromJson(data.getData(), CommonServiceNode.class));
                    break;
                case NODE_UPDATED:
                //更新服务的监听节点
                    listener.onServiceUpdated(dataPath, Jsons.fromJson(data.getData(), CommonServiceNode.class));
                    break;
            }
            Logs.RSD.info("ZK node data change={}, nodePath={}, watchPath={}, ns={}");
        }
    }
}
