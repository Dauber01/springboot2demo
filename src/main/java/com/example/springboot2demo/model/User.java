package com.example.springboot2demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 用户演示实体类
 *
 * @author Lucifer
 * @date 2018／06／20 22:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String phone;
    private String email;
    private String name;
    private Date birthday;

}
