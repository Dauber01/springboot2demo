package com.example.springboot2demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Lucifer
 * @do 长连接的响应bean
 * @date 2018/06/21 11:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "event")
public class MyEvent {

    @Id
    private Long id;

    private String message;

}
