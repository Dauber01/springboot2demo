package com.example.springboot2demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 开始测试
 *
 * @author Lucifer
 * @date 2018／06／20 19:41
 */
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public Mono<String> hello(){
        log.info("get 1 start");
        Mono<String> result = Mono.fromSupplier(() -> createStr());
        log.info("get 1 end");
        return result;
    }

    @GetMapping("/hello1")
    public String hello1(){
        log.info("get 1 start");
        String result = createStr();
        log.info("get 1 end");
        return result;

    }

    @GetMapping(value = "/3", produces = "text/event-stream")
    public Flux<String> test3(){
        Flux<String> flux = Flux.fromStream(IntStream.range(1, 6).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
            }
            System.out.println(i);
            return "this is the -- " + i;
        }));
        return flux;
    }

    private String createStr(){
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            log.error(e.getMessage());
        }
        return "welcome to zhe spring boot 2 test!";
    }

}
