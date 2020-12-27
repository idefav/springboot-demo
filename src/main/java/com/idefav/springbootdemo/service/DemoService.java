package com.idefav.springbootdemo.service;

import com.idefav.springbootdemo.instrument.Traced;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class DemoService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DemoService demoService;

    @Traced
    public boolean saveDb() {
        log.info("保存 DB 成功");
        return true;
    }

    @Traced
    public boolean accessHttpbinHeaders() {
        log.info("开始访问 httpbin 服务");
        String result = restTemplate.getForObject("http://httpbin.default.svc/headers", String.class);
        log.info("访问 httpbin 服务成功,返回: {}", result);
        demoService.saveDb();
        return true;
    }

    @Traced
    public boolean accessHttpbinDelay(Integer deplaySecends) {
        log.info("开始访问 httpbin 服务");
        String result = restTemplate.getForObject("http://httpbin.default.svc/delay/" + deplaySecends, String.class);
        log.info("访问 httpbin 服务成功,返回: {}", result);
        demoService.saveDb();
        return true;
    }

    @Traced
    public boolean accessHttpbinIp() {
        log.info("开始访问 httpbin 服务");
        String result = restTemplate.getForObject("http://httpbin.default.svc/ip", String.class);
        log.info("访问 httpbin 服务成功,返回: {}", result);
        demoService.saveDb();
        return true;
    }

    @Traced
    public boolean accessHttpbinGet() {
        log.info("开始访问 httpbin 服务");
        String result = restTemplate.getForObject("http://httpbin.default.svc/get", String.class);
        log.info("访问 httpbin 服务成功,返回: {}", result);
        demoService.saveDb();
        return true;
    }

    @Traced
    public boolean accessHttpbinStatus(HttpStatus status) {
        log.info("开始访问 httpbin 服务");
        String result = restTemplate.getForObject("http://httpbin.default.svc/status/" + status.value(), String.class);
        log.info("访问 httpbin 服务成功,返回: {}", result);
        demoService.saveDb();
        return true;
    }

    @Traced
    public boolean accessFlaskAppVersion() {
        log.info("开始访问 flaskapp 服务");
        log.info("开始访问flaskapp 服务");
        // 访问 flaskapp/env/version 接口
        String result = restTemplate.getForObject("http://flaskapp.default.svc/env/version", String.class);
        log.info("访问 flaskapp 服务成功, 返回:{}", result);
        return true;
    }

    @Traced
    public boolean accessFlaskAppHostName() {
        log.info("开始访问 flaskapp 服务");
        log.info("开始访问flaskapp 服务");
        // 访问 flaskapp/env/version 接口
        String result = restTemplate.getForObject("http://flaskapp.default.svc/env/HOSTNAME", String.class);
        log.info("访问 flaskapp 服务成功, 返回:{}", result);
        return true;
    }

    @Traced
    public boolean accessFlaskAppFetchWithHeader(String url) {
        log.info("开始访问 flaskapp 服务");
        log.info("开始访问flaskapp 服务");
        // 访问 flaskapp/env/version 接口
        String result = restTemplate.getForObject("http://flaskapp.default.svc/fetch_with_header?url=" + url, String.class);
        log.info("访问 flaskapp 服务成功, 返回:{}", result);
        return true;
    }
}
