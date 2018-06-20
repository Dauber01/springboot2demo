package com.example.springboot2demo.repository;

import com.example.springboot2demo.document.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * user对象的repository
 *
 * @author Lucifer
 * @date 2018／06／20 22:21
 */
public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Mono<User> findByUsername(String username);

    Mono<Long> deleteByUsername(String username);
}
