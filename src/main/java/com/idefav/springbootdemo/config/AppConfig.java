package com.idefav.springbootdemo.config;

import io.opentracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Tracer jaegerTracer() {
        // The following environment variables need to set
        // JAEGER_ENDPOINT="http://10.42.126.171:28019/api/traces"
        // JAEGER_PROPAGATION="b3"
        // JAEGER_TRACEID_128BIT="true" Use 128bit tracer id to be compatible with the
        // trace id generated by istio/envoy
        return io.jaegertracing.Configuration.fromEnv("springboot-demo").getTracer();
    }
}
