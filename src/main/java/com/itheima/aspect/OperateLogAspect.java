package com.itheima.aspect;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.itheima.contexts.BaseContext;
import com.itheima.pojo.entity.OperateLog;
import com.itheima.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Slf4j
@Aspect
public class OperateLogAspect {

    private final OperateLogService operateLogService;

    public OperateLogAspect(OperateLogService operateLogService) {
        this.operateLogService = operateLogService;
    }

    @Pointcut("@annotation(com.itheima.annotations.OperateLog)")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("记录操作日志开始...");
        //获取操作时间
        LocalDateTime now = LocalDateTime.now();
        //获取操作的类名
        String className = joinPoint.getSignature().getClass().getName();
        //获取操作的方法名
        String methodName = joinPoint.getSignature().getName();
        //获取方法的参数
        Object[] args = joinPoint.getArgs();
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        //获取操作耗时
        long costTime = end - begin;
        //获取返回值
        String jsonString = JSONObject.toJSONString(result);

        OperateLog operateLog = new OperateLog();
        operateLog.setOperateTime(now);
        operateLog.setClassName(className);
        operateLog.setMethodName(methodName);
        operateLog.setMethodParams(Arrays.toString(args));
        operateLog.setReturnValue(jsonString);
        operateLog.setCostTime(costTime);
        operateLog.setOperateUser(Math.toIntExact(BaseContext.getCurrentId()));
        operateLogService.save(operateLog);

        log.info("记录操作日志结束...");
        return result;
    }



}
