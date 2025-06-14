package com.itheima.interceptor;


import com.itheima.Utils.JwtUtils;
import com.itheima.constant.EmplConstant;
import com.itheima.contexts.BaseContext;
import com.itheima.properties.JwtProperties;
import com.itheima.result.Result;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtAdminInterceptor implements HandlerInterceptor {

    @Autowired
    JwtProperties jwtProperties;


    /**
     * 拦截器方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response , Object handler){
        log.info("开始进行用户认证...");
        //判断当前拦截的是否为controller的方法
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        //从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());
        //校验令牌
        try {
            Claims claims = JwtUtils.parseJWT(jwtProperties.getAdminSecretKey(),token);
            //todo 解析出的令牌信息保存到当前线程的变量中
            Long empId = Long.valueOf(claims.get(EmplConstant.EMP_ID).toString());
            String username = claims.get(EmplConstant.USERNAME).toString();
            log.info("{}用户id为：{}",username,empId);
            BaseContext.setCurrentId(empId);
            return true;
        } catch (Exception e) {
            //返回状态码
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Result.error("NOT_LOGIN");
            return false;
        }
    }


}
