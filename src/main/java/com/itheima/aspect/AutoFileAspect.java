package com.itheima.aspect;


import com.itheima.annotations.AutoFile;
import com.itheima.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFileAspect {

    /**
     * 切入点
     */
    @Pointcut("execution(* com.itheima.mapper.*.*(..)) && @annotation(com.itheima.annotations.AutoFile)")
    public void autoFilePointCut(){}

    /**
     * 前置通知
     */
    @Before("autoFilePointCut()")
    public void autoFile(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        log.info("开始进行公共字段填充...");
        //获取注解上的操作数据的类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFile autoFile = signature.getMethod().getAnnotation(AutoFile.class);
        OperationType value = autoFile.value();

        //获取方法上的参数
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0){
            return;
        }
        Object object = args[0];

        //准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        //todo 获取当前登录用户

        if (value == OperationType.INSERT){
            Method setCreateTime = object.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
            Method setUpdateTime = object.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);

            setCreateTime.invoke(object, now);
            setUpdateTime.invoke(object, now);
        }else if (value == OperationType.UPDATE){

            Method setCreateTime = object.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
            Method setUpdateTime = object.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);

            setCreateTime.invoke(object, now);
            setUpdateTime.invoke(object, now);
        }
    }
}
