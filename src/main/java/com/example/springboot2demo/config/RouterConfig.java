package com.example.springboot2demo.config;

import com.example.springboot2demo.handler.TimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import java.awt.*;

/**
 * bean配置
 * @author Lucifer
 * @date 2018／06／20 20:08
 */
@Configuration
public class RouterConfig {

    @Autowired
    private TimeHandler timeHandler;

    /**
     * @do 配置bean拦截对应的请求
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> timeRouter(){
        return RouterFunctions.route(RequestPredicates.GET("/getTime"), serverRequest -> timeHandler.getTime(serverRequest))
                .andRoute(RequestPredicates.GET("/getDate"), timeHandler::getDate)
                .andRoute(RequestPredicates.GET("/times"), timeHandler::sendTimePerSec);
    }

    @Bean
    public RouterFunction<ServerResponse> myEventRouter(TimeHandler timeHandler){
        return RouterFunctions.nest(RequestPredicates.path("/user"),
                RouterFunctions.route(RequestPredicates.POST("/")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), timeHandler::createMyEvent))
                .andRoute(RequestPredicates.DELETE("/{id}"), timeHandler::deleteMyEvent);
    }

}
