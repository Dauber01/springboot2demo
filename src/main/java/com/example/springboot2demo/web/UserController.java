package com.example.springboot2demo.web;

import com.example.springboot2demo.model.User;
import com.example.springboot2demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * user的操作接口
 *
 * @author Lucifer
 * @date 2018／06／20 22:53
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public Mono<User> save(@RequestBody User user){
        return this.userService.save(user);
    }

    @GetMapping("/{username}")
    public Mono<User> findByUsername(@PathVariable String username){
        return this.userService.findByUsername(username);
    }

    @DeleteMapping("/{username}")
    public Mono<Long> deleteByUsername(@PathVariable String username){
        return this.userService.deleteByUsername(username);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> findAll(){
        return this.userService.findAll().delayElements(Duration.ofSeconds(2));
    }


}
