package com.idefav.springbootdemo;

import com.idefav.rest.lb.interceptors.LoadBalancerInterceptor;
import com.idefav.rest.lb.recovery.AbstractRecoveryCallback;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.retry.RetryContext;


public class DemoRecoveryCallback extends AbstractRecoveryCallback {


    @Override
    protected ClientHttpResponse invoke(RetryContext retryContext, LoadBalancerInterceptor.LoadBalancerHttpReqeustWrapper loadBalancerHttpReqeustWrapper, ClientHttpResponse clientHttpResponse) {
        System.out.println("降级");
        return clientHttpResponse;
    }
}
