package com.example.springboot2demo.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/*@SpringBootTest
@RunWith(SpringRunner.class)*/
public class HelloControllerTest {

    @Test
    public void hello() throws Exception {
        WebClient webClient = WebClient.create("http://localhost:8080");
        Mono<String> resp = webClient.get().uri("/hello")
                .retrieve() //返回值异步的返回
                .bodyToMono(String.class);
        resp.subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(1);
    }

}