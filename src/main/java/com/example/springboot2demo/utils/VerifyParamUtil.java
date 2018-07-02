package com.example.springboot2demo.utils;

import com.example.springboot2demo.exceptions.VerifyException;

import javax.xml.bind.ValidationException;
import java.util.stream.Stream;

/**
 * 自定义校验参数的util
 *
 * @author Lucifer
 * @date 2018／07／02 21:34
 */
public class VerifyParamUtil {

    /** 不允许使用的名字. */
    public static final String[] NOT_ALLOW_NAME = {"abc", "aaa", "bbb"};

    /**
     * @do 校验名字,不成功则抛出异常
     * @param name
     */
    public static void verifyNameFormat(String name){
        Stream.of(NOT_ALLOW_NAME).filter(n -> name.equals(n))
                .findAny().ifPresent(n -> {
                    throw new VerifyException("name", name);
        });
    }

}
