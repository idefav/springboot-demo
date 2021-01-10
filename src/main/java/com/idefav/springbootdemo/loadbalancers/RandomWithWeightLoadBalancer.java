package com.idefav.springbootdemo.loadbalancers;

import com.idefav.rest.lb.LbServer;
import com.idefav.rest.lb.loadbalancers.AbstractLoadBalancer;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class RandomWithWeightLoadBalancer extends AbstractLoadBalancer {

    private TreeMap<Double, LbServer> weightMap = new TreeMap<>();

    public RandomWithWeightLoadBalancer(List<LbServer> serverList) {
        super(serverList);
        checkServerList();
        serverList.forEach(k -> {
            double lastWeight = this.weightMap.size() == 0 ? 0 : this.weightMap.lastKey();
            if (k.getProperties() == null) {
                return;
            }
            String weight = k.getProperties().get("weight");
            if (StringUtils.isEmpty(weight)) {
                return;
            }
            if (!StringUtils.isNumeric(weight)) {
                return;
            }
            weightMap.put(Double.parseDouble(weight) + lastWeight, k);
        });
    }

    @Override
    public LbServer getServer() {
        double randomWeight = weightMap.lastKey() * Math.random();
        NavigableMap<Double, LbServer> doubleLbServerNavigableMap = weightMap.tailMap(randomWeight, false);
        return weightMap.get(doubleLbServerNavigableMap.firstKey());
    }
}
