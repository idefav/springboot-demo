package com.idefav.springbootdemo;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * the RandomLoadBancor description.
 *
 * @author ${USER}
 */
public class RandomLoadBancor implements Loadbancor {
    private final Random random = new Random();
    private List<String> serverList;

    public RandomLoadBancor(List<String> serverList) {
        this.serverList = serverList;
    }

    @Override
    public String getServer() {
        if (CollectionUtils.isEmpty(serverList)) {
            throw new RuntimeException("没有可用服务列表");
        }
        int i = random.nextInt(1024) % serverList.size();
        return serverList.get(i);
    }
}
