package com.example.springboot2demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 开始测试
 *
 * @author Lucifer
 * @date 2018／06／20 19:41
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Mono<String> hello(){
        return Mono.just("welcome to zhe spring boot 2 test!");
    }

}
