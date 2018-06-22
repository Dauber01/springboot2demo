package com.example.springboot2demo.web;

import com.example.springboot2demo.model.MyEvent;
import com.example.springboot2demo.repository.MyEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Lucifer
 * @do 长相应的接口
 * @date 2018/06/21 11:18
 */
@RestController
@RequestMapping("/event")
public class MyEventController {

    @Autowired
    private MyEventRepository myEventRepository;

    @PostMapping(path = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<Void> loadEvents(@RequestBody Flux<MyEvent> myEventFlux){
        return myEventRepository.insert(myEventFlux).then();
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<MyEvent> getEvents(){
        return myEventRepository.findBy();
    }

}
