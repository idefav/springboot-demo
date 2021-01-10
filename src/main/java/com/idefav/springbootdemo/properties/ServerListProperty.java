package com.idefav.springbootdemo.properties;

import com.idefav.springbootdemo.loadbalancers.RandomLoadBancor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the ServerListProperty description.
 *
 * @author ${USER}
 */
@Data
@ConfigurationProperties(prefix = "service")
public class ServerListProperty {

    private Map<String, ServiceConfig> serviceList = new HashMap<>();
    private Map<String, ServiceConfig2> serviceList2 = new HashMap<>();

    @Data
     class ServiceConfig2 {
        private List<String> serverList;
        private Class<?> loadBancor = RandomLoadBancor.class;
    }
}
