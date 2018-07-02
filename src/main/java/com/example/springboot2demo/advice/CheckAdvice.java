package com.example.springboot2demo.advice;

import com.example.springboot2demo.exceptions.VerifyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * 对校验住的参数进行拦截,并进行转换后返回前台
 * @author Lucifer
 * @date 2018／07／02 21:10
 */
@ControllerAdvice
public class CheckAdvice {

    /**
     * @do 对错误的信息进行拦截,并进行信息处理
     * @param ex 错误的异常信息
     * @return response响应体
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handleBindException(WebExchangeBindException ex){
        return new ResponseEntity<String>(toStr(ex), HttpStatus.BAD_REQUEST);
    }

    /**
     * @do 对错误的信息进行拦截,并进行信息处理
     * @param ex 错误的异常信息
     * @return response响应体
     */
    @ExceptionHandler(VerifyException.class)
    public ResponseEntity<String> handleBindVerifyException(VerifyException ex){
        return new ResponseEntity<String>(toStr(ex), HttpStatus.BAD_REQUEST);
    }

    /**
     * @do 将错误信息中的数据进行格式化,并返回
     * @param ex 异常
     * @return string 返回的错误数据
     */
    public String toStr(WebExchangeBindException ex){
        return ex.getFieldErrors().stream()
                .map(e -> e.getField() + " : " +e.getDefaultMessage())
                .reduce("", (a, b) -> a + "\n" +b);
    }

    public String toStr(VerifyException ex){
        return ex.getName() + ",错误的值 :" +ex.getValue();
    }

}
