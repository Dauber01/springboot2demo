package com.example.springboot2demo.web;

import com.example.springboot2demo.model.MyEvent;
import com.example.springboot2demo.repository.MyEventRepository;
import com.example.springboot2demo.utils.VerifyParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

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

    @PostMapping("/save")
    public Mono<MyEvent> save(@Valid @RequestBody MyEvent myEvent){
        VerifyParamUtil.verifyNameFormat(myEvent.getMessage());
        return myEventRepository.save(myEvent);
    }

    /**
     * @do 删除对应id的数据,假如删除成功,返回200,假如删除失败,则返回404
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id")String id){
        return this.myEventRepository.findById(Long.valueOf(id))
                //当你要操作一个数据,并返回Mono的时候,用flatMap,当你只进行转换的时候用map
                .flatMap(user -> this.myEventRepository.delete(user)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public Mono<ResponseEntity<MyEvent>> update(@RequestBody MyEvent myEvent){
        return this.myEventRepository.findById(myEvent.getId())
                .flatMap(u -> {
                    u.setId(myEvent.getId());
                    u.setMessage(myEvent.getMessage());
                    return this.myEventRepository.save(u);
                })
                .map(u -> new ResponseEntity<MyEvent>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<MyEvent>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/test")
    public void test(){
        MyEvent myEvent = new MyEvent();
        myEvent.setId(1L);
        myEventRepository.delete(myEvent);
    }

    /**
     * @do 通过id对对象进行查找,有对象的话返回对象并将状态设置为200,如查无数据,则返回404
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<MyEvent>> findById(@PathVariable("id") String id){
        return this.myEventRepository.findById(Long.valueOf(id))
                .map(r -> new ResponseEntity<MyEvent>(r, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<MyEvent>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{start}/{end}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<MyEvent> findByIdScope(@PathVariable("start") String start,
          @PathVariable("end") String end){
        return this.myEventRepository.findByIdBetween(Long.valueOf(start), Long.valueOf(end));
    }

    @GetMapping(value = "/old", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<MyEvent> findByScope(){
        return this.myEventRepository.findId();
    }

}
