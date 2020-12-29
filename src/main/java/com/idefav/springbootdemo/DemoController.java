package com.idefav.springbootdemo;

import com.idefav.springbootdemo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/demo")
@Slf4j
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DemoService demoService;

    @GetMapping("/access")
    public String access() {
        log.info("开始访问flaskapp 服务");
        // 访问 flaskapp/env/version 接口
        String result = restTemplate.getForObject("http://flaskapp.default.svc/env/version", String.class);
        log.info("访问 flaskapp 服务成功, 返回:{}", result);
        log.info("访问 httpbin 访问");
        demoService.accessFlaskAppHostName();
        demoService.accessFlaskAppFetchWithHeader("http://httpbin.default.svc/get");
        demoService.accessHttpbinDelay(1);
        demoService.accessFlaskAppHostName();
        demoService.accessHttpbinIp();
        demoService.saveDb();
        log.info("访问httpbin 访问成功");
        return result;
    }

    @GetMapping("/baidu")
    public String accessBaidu() {
        return demoService.accessBaidu();
    }

}
