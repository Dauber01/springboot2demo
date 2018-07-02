package com.example.springboot2demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 校验的异常
 *
 * @author Lucifer
 * @date 2018／07／02 21:39
 */
@Data
@AllArgsConstructor
public class VerifyException extends RuntimeException{

    /** 错误的名字. */
    private String name;

    /** 错误的值. */
    private String value;

}
