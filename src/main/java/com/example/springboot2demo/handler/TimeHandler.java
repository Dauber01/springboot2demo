package com.example.springboot2demo.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.example.springboot2demo.model.MyEvent;
import com.example.springboot2demo.repository.MyEventRepository;
import com.example.springboot2demo.utils.VerifyParamUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MyEventRepository myEventRepository;

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

    /**
     * @do 对要保存的信息进行保存
     * @param request 请求
     * @return response 返回
     */
    public Mono<ServerResponse> createMyEvent(ServerRequest request){
        Mono<MyEvent> mono = request.bodyToMono(MyEvent.class);
        return mono.flatMap(m -> {
            //校验的代码需要放到这里
            VerifyParamUtil.verifyNameFormat(m.getMessage());
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(myEventRepository.save(m), MyEvent.class);
        });
    }

    /**
     * @do 删除对应的信息
     * @param request 请求
     * @return 返回
     */
    public Mono<ServerResponse> deleteMyEvent(ServerRequest request){
        String id = request.pathVariable("id");
        return this.myEventRepository.findById(Long.valueOf(id))
                .then(ServerResponse.ok().build())
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
