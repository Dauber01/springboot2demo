package com.example.springboot2demo.web;

import com.example.springboot2demo.model.MyEvent;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.junit.Assert.*;

/**
 * to do
 *
 * @author Lucifer
 * @date $(DATE)
 */
public class MyEventControllerTest {

    @Test
    public void loadEvents() throws Exception {
        Flux<MyEvent> myEventFlux = Flux.interval(Duration.ofSeconds(1))
                .map(e -> new MyEvent(System.currentTimeMillis(), "message-" + e)).take(5);
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient.post().uri("/event")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(myEventFlux, MyEvent.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Test
    public void getEvents() throws Exception {
    }

}