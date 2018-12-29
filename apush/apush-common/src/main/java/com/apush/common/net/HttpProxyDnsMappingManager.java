package com.apush.common.net;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.apush.api.service.BaseService;
import com.apush.api.service.Listener;
import com.apush.api.spi.Spi;
import com.apush.api.spi.common.ServiceDiscoveryFactory;
import com.apush.api.spi.net.DnsMapping;
import com.apush.api.spi.net.DnsMappingManager;
import com.apush.api.srd.ServiceDiscovery;
import com.apush.api.srd.ServiceListener;
import com.apush.api.srd.ServiceNode;
import com.apush.tools.Jsons;
import com.apush.tools.config.CC;
import com.apush.tools.thread.NamedPoolThreadFactory;
import com.apush.tools.thread.ThreadNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.apush.api.srd.ServiceNames.DNS_MAPPING;
import static com.apush.tools.Utils.checkHealth;



/**
 * http消息代理Dns（也就是去找能用的服务，使用短链接心跳维持）
 * @author madongyu-ds
 *
 */
@Spi(order = 1)
public class HttpProxyDnsMappingManager extends BaseService implements DnsMappingManager, Runnable, ServiceListener {
    private final Logger logger = LoggerFactory.getLogger(HttpProxyDnsMappingManager.class);

    protected final Map<String, List<DnsMapping>> mappings = Maps.newConcurrentMap();

    private final Map<String, List<DnsMapping>> all = Maps.newConcurrentMap();
    private Map<String, List<DnsMapping>> available = Maps.newConcurrentMap();

    private ScheduledExecutorService executorService;

    @Override
    protected void doStart(Listener listener) throws Throwable {
    	//服务发现
        ServiceDiscovery discovery = ServiceDiscoveryFactory.create();
        discovery.subscribe(DNS_MAPPING, this);
        discovery.lookup(DNS_MAPPING).forEach(this::add);

        if (all.size() > 0) {
            executorService = Executors.newSingleThreadScheduledExecutor(
                    new NamedPoolThreadFactory(ThreadNames.T_HTTP_DNS_TIMER)
            );
            executorService.scheduleAtFixedRate(this, 1, 20, TimeUnit.SECONDS); //20秒 定时扫描dns
        }
        listener.onSuccess();
    }

    @Override
    protected void doStop(Listener listener) throws Throwable {
        if (executorService != null) {
            executorService.shutdown();
        }
        listener.onSuccess();
    }

    @Override
    public void init() {
        all.putAll(CC.mp.http.dns_mapping);
        available.putAll(CC.mp.http.dns_mapping);
    }

    @Override
    public boolean isRunning() {
        return executorService != null && !executorService.isShutdown();
    }

    public void update(Map<String, List<DnsMapping>> nowAvailable) {
        available = nowAvailable;
    }

    public Map<String, List<DnsMapping>> getAll() {
        return all;
    }

    public DnsMapping lookup(String origin) {
        List<DnsMapping> list = mappings.get(origin);

        if (list == null || list.isEmpty()) {
            if (available.isEmpty()) return null;
            list = available.get(origin);
        }

        if (list == null || list.isEmpty()) return null;
        int L = list.size();
        if (L == 1) return list.get(0);
        return list.get((int) (Math.random() * L % L));
    }

    @Override
    public void run() {//20秒一扫描，发现服务
        logger.debug("do dns mapping checkHealth ...");
        Map<String, List<DnsMapping>> all = this.getAll();
        Map<String, List<DnsMapping>> available = Maps.newConcurrentMap();
        all.forEach((key, dnsMappings) -> {
            List<DnsMapping> okList = Lists.newArrayList();
            dnsMappings.forEach(dnsMapping -> {
                if (checkHealth(dnsMapping.getIp(), dnsMapping.getPort())) {
                    okList.add(dnsMapping);
                } else {
                    logger.warn("dns can not reachable:" + Jsons.toJson(dnsMapping));
                }
            });
            available.put(key, okList);
        });
        this.update(available);
    }

    @Override
    public void onServiceAdded(String path, ServiceNode node) {
        add(node);
    }

    @Override
    public void onServiceUpdated(String path, ServiceNode node) {
        add(node);
    }

    @Override
    public void onServiceRemoved(String path, ServiceNode node) {
        mappings.computeIfAbsent(node.getAttr("origin"), k -> new ArrayList<>())
                .remove(new DnsMapping(node.getHost(), node.getPort()));
    }

    private void add(ServiceNode node){
        mappings.computeIfAbsent(node.getAttr("origin"), k -> new ArrayList<>())
                .add(new DnsMapping(node.getHost(), node.getPort()));
    }
}
