package com.example.springboot2demo.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * 基于时间格式的函数
 *
 * @author Lucifer
 * @date 2018／06／20 19:52
 */
@Component
public class TimeHandler {

    /**
     * @do 返回时间的函数实现
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> getTime(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("now is" + new SimpleDateFormat("hh:MM:ss").format(new Date())), String.class);
    }

    /**
     * @do 返回日期的函数实现
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> getDate(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Now date is" + new SimpleDateFormat("yyyy-mm-dd").format(new Date())), String.class);
    }

    /**
     * @do 进行一次请求后端对前端的持续响应通讯
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> sendTimePerSec(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Flux.interval(Duration.ofSeconds(1))
                .map(e -> new SimpleDateFormat("hh:MM:ss").format(new Date())), String.class);
    }

}
