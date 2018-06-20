package com.example.springboot2demo.web;

import com.example.springboot2demo.document.User;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.Assert.*;

public class UserControllerTest {

    @Test
    public void save() throws Exception {
    }

    @Test
    public void findByUsername() throws Exception {
    }

    @Test
    public void deleteByUsername() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
        webClient.get().uri("/user")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange() //获取response信息，返回值为ClientResponse，retrive()可以看做是exchange()方法的“快捷版”
                .flatMapMany(e -> e.bodyToFlux(User.class))
                .doOnNext(System.out::println) //只读地peek每个元素，然后打印出来，它并不是subscribe，所以不会触发流；
                .blockLast(); //上个例子中sleep的方式有点low，blockLast方法，顾名思义，在收到最后一个元素前会阻塞，响应式业务场景中慎用。
    }

}