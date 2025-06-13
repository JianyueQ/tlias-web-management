package com.itheima.handler;

import com.itheima.Exception.BaseException;
import com.itheima.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result error(BaseException e) {
        log.info("异常信息:{}", e.getMessage());
        return Result.error("服务器异常");
    }

    /**
     * 处理sql异常
     */
    @ExceptionHandler
    public Result error(SQLIntegrityConstraintViolationException ex) {
        log.info("异常信息{}", ex.getMessage());
        String message = ex.getMessage();
        if (message.contains("Duplicate entry")){
            String[] split = message.split(" ");
            String name = split[2];
            return Result.error(name+"已存在");
        }else {
            return Result.error("服务器异常");
        }
    }
}
