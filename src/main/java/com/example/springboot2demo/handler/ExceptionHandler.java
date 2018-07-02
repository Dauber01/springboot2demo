package com.example.springboot2demo.handler;

import com.example.springboot2demo.exceptions.VerifyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;



/**
 * 异常的捕捉
 * @author Lucifer
 * @date 2018／07／02 23:16
 */
@Slf4j
@Component
@Order(-2)
public class ExceptionHandler implements WebExceptionHandler{

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
        String str = exceptionToStr(throwable);
        DataBuffer dataBuffer = response.bufferFactory().wrap(str.getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    private String exceptionToStr(Throwable e){
        if (e instanceof VerifyException){
            VerifyException verifyException = (VerifyException)e;
            return verifyException.getName() + " : " + verifyException.getValue();
        }else{
            e.printStackTrace();
            log.error(e.toString());
            return e.getMessage();
        }
    }

}
