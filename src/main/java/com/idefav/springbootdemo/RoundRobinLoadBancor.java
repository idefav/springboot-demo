package com.idefav.springbootdemo;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * the RoundRobin description.
 *
 * @author ${USER}
 */
public class RoundRobinLoadBancor implements Loadbancor {

    private AtomicInteger index = new AtomicInteger(0);

    private List<String> serverList;

    public RoundRobinLoadBancor(List<String> serverList) {
        this.serverList = serverList;
    }

    @Override
    public String getServer() {
        if (CollectionUtils.isEmpty(serverList)) {
            throw new RuntimeException("没有可用服务列表");
        }
        int size = serverList.size();
        int i = this.index.get() % size;
        this.index.compareAndSet(index.get(), i + 1);
        return serverList.get(i);
    }
}
