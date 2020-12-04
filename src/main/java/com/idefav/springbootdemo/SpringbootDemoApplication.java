package com.idefav.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

/**
 * The type Springboot demo application.
 */
@SpringBootApplication
@RestController
@RequestMapping("/api")
public class SpringbootDemoApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }


    /**
     * Hello string.
     *
     * @return the string
     */
    public String hello(){
        WebSocketSession webSocketSession;

        return String.format("hhh,%s","test");
    }
}
