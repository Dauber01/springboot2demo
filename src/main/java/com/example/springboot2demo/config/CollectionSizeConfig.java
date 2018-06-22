package com.example.springboot2demo.config;

import com.example.springboot2demo.model.MyEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * @author Lucifer
 * @do @Tailable仅支持有大小限制的（“capped”）collection，而自动创建的collection是不限制大小的，因此我们需要先手动创建。Spring Boot提供的CommandLineRunner可以帮助我们实现这一点
 * @date 2018/06/21 14:55
 */
@Configuration
public class CollectionSizeConfig {

    @Bean
    public CommandLineRunner initData(MongoOperations mongoOperations){
        return (String... args) -> { //CommandLineRunner也是一个函数式接口，其实例可以用lambda表达；
            mongoOperations.dropCollection(MyEvent.class); //如果有，先删除collection，生产环境慎用这种操作；
            mongoOperations.createCollection(MyEvent.class, CollectionOptions.empty().maxDocuments(200).size(100000).capped()); //创建一个记录个数为10的capped的collection，容量满了之后，新增的记录会覆盖最旧的。
        };
    }

}
