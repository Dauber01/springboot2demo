package com.example.springboot2demo.repository;

import com.example.springboot2demo.model.MyEvent;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

/**
 * @author Lucifer
 * @do 长相应bean的dao
 * @date 2018/06/21 11:15
 */
public interface MyEventRepository extends ReactiveMongoRepository<MyEvent, Long> {

    @Tailable
    Flux<MyEvent> findBy();

    Flux<MyEvent> findByIdBetween(long start, long end);

    @Query("{'id':{'$gte':0, '$lte':3}}")
    Flux<MyEvent> findId();

}
