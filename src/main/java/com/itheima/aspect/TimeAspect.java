package com.itheima.aspect;


import com.itheima.annotations.RecordTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class TimeAspect {

    @Pointcut("execution(* com.itheima.controller.*.*(..))  && @annotation(com.itheima.annotations.RecordTime)")
    public void pointCut() {
    }


    @Around("pointCut()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {

        //方法执行前时间
        long beginTime = System.currentTimeMillis();

        //调用原始方法运行
        Object result = joinPoint.proceed();

        //方法执行后时间
        long endTime = System.currentTimeMillis();
        log.info("{}方法执行耗时：{}ms", joinPoint.getSignature(), endTime - beginTime);

        return result;
    }

    /**
     * 在spring中用JoinPoint抽象了连接点,用它可以获得方法执行时的相关信息,如:目标类名,方法名,方法参数;
     * 对于@Around类型的通知,获取连接点信息的方法参数只能是ProceedingJoinPoint;
     * 对于其他四种通知,获取连接点信息方法参数只能是JoinPoint,它是ProceedingJoinPoint的父类型;
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature(); //获取目标方法签名

        String className = joinPoint.getTarget().getClass().getName(); //获取目标类名

        String MethodName = joinPoint.getSignature().getName(); //获取目标方法名

        Object[] args = joinPoint.getArgs(); //获取目标方法运行参数

        Object proceed = joinPoint.proceed();//调用原始方法运行

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); //获取目标方法签名

        Method method = methodSignature.getMethod();//获取目标方法

        RecordTime annotation = method.getAnnotation(RecordTime.class); //获取目标方法注解

        /*
          目标方法签名:Result com.itheima.controller.DeptController.list(),
          目标类名:com.itheima.controller.DeptController,
          目标方法名:list,
          目标方法运行参数:[],调用原始方法运行:Result(code=1, message=操作成功, data=[DeptVo(), DeptVo(), DeptVo()])
         */
        log.info("目标方法签名:{},目标类名:{},目标方法名:{},目标方法运行参数:{},调用原始方法运行:{}", signature, className, MethodName, args, proceed);

        /*
            (MethodSignature)目标方法签名:Result com.itheima.controller.DeptController.list(),
            (methodSignature)目标方法public com.itheima.result.Result com.itheima.controller.DeptController.list(),
            目标方法注解@com.itheima.annotations.RecordTime()
         */
        log.info("(MethodSignature)目标方法签名:{},(methodSignature)目标方法{},目标方法注解{}", methodSignature, method, annotation);

        return proceed;
    }


}
