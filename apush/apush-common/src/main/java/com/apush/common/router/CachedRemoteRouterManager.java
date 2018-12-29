package com.apush.common.router;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**Guava Cache
 * 远程路由器缓存管理类      
 * 他们很相似，但也不完全一样。最基本的区别是ConcurrentMap会一直保存所有添加的元素，直到显式地移除。相对地，Guava Cache为了限制内存占用，通常都设定为自动回收元素。在某些场景下，尽管LoadingCache 不回收元素，它也是很有用的，因为它会自动加载缓存。
 * @author madongyu-ds
 *
 */
public final class CachedRemoteRouterManager extends RemoteRouterManager {
    private final Cache<String, Set<RemoteRouter>> cache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .expireAfterAccess(5, TimeUnit.MINUTES)
            .build();

    @Override
    public Set<RemoteRouter> lookupAll(String userId) {
        Set<RemoteRouter> cached = cache.getIfPresent(userId);
        if (cached != null) return cached;
        Set<RemoteRouter> remoteRouters = super.lookupAll(userId);
        if (remoteRouters != null) {
            cache.put(userId, remoteRouters);
        }
        return remoteRouters;
    }

    @Override
    public RemoteRouter lookup(String userId, int clientType) {
        Set<RemoteRouter> cached = this.lookupAll(userId);
        for (RemoteRouter remoteRouter : cached) {
            if (remoteRouter.getRouteValue().getClientType() == clientType) {
                return remoteRouter;
            }
        }
        return null;
    }

    /**
     * 如果推送失败，可能是缓存不一致了，可以让本地缓存失效
     * <p>
     * 失效对应的本地缓存
     *
     * @param userId
     */
    public void invalidateLocalCache(String userId) {
        if (userId != null) cache.invalidate(userId);
    }
}
