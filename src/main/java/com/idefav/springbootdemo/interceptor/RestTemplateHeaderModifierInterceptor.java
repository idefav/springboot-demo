package com.idefav.springbootdemo.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * the RestTemplateHeaderModifierInterceptor description.
 *
 * @author ${USER}
 */
public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        ClientHttpResponse httpResponse = clientHttpRequestExecution.execute(httpRequest, body);
        httpResponse.getHeaders().add("hello", "world");
        return httpResponse;
    }
}
