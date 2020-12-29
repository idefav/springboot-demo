package com.idefav.springbootdemo.interceptor;

import com.idefav.springbootdemo.Loadbancor;
import com.idefav.springbootdemo.RandomLoadBancor;
import com.idefav.springbootdemo.properties.ServerListProperty;
import com.idefav.springbootdemo.properties.ServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import rawhttp.core.RawHttp;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * the UrlOverriderInterceptor description.
 *
 * @author ${USER}
 */
@Slf4j
public class UrlOverriderInterceptor implements ClientHttpRequestInterceptor {
    private static Map<String, Loadbancor> serviceLoadBancorList = new HashMap<>();
    private Map<String, ServiceConfig> serviceList = new HashMap<>();

    Random random = new Random();

    public UrlOverriderInterceptor(Map<String, ServiceConfig> serviceList) {
        this.serviceList = serviceList;
    }

    public Loadbancor getLoadbancor(String serviceId) {
        if (serviceLoadBancorList.containsKey(serviceId)) {
            return serviceLoadBancorList.get(serviceId);
        }
        ServiceConfig serviceConfig = serviceList.get(serviceId);
        Loadbancor newInstance = new RandomLoadBancor(serviceList.get(serviceId).getServerList());
        try {
            Constructor<?> constructor = serviceConfig.getLoadBancor().getConstructor(List.class);
            newInstance = (Loadbancor) constructor.newInstance(serviceConfig.getServerList());
            serviceLoadBancorList.put(serviceId, newInstance);
            return newInstance;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        serviceLoadBancorList.put(serviceId, newInstance);
        return newInstance;

    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        return clientHttpRequestExecution.execute(new UrlOverriderHttpReqeustWrapper(httpRequest), bytes);
    }

    public class UrlOverriderHttpReqeustWrapper extends HttpRequestWrapper {


        public UrlOverriderHttpReqeustWrapper(HttpRequest request) {
            super(request);
        }


        @Override
        public URI getURI() {
            URI oldUri = super.getURI();
            String serviceId = oldUri.getHost();
            Loadbancor loadBancor = getLoadbancor(serviceId);
            String server = loadBancor.getServer();
            return RawHttp.replaceHost(oldUri, server);
        }
    }

}
