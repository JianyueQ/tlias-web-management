package com.itheima.Filter;


import com.itheima.Utils.JwtUtils;
import com.itheima.constant.EmplConstant;
import com.itheima.contexts.BaseContext;
import com.itheima.properties.JwtProperties;
import com.itheima.result.Result;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter("/*")
//@Component
public class LoginCheckFilter implements Filter {

//    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //获取请求路径
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();


        //判断当前请求路径是否为登录请求,如果是登录请求则放行
        if (url.contains("/login")) {
            log.info("当前请求为登录请求,放行");
            filterChain.doFilter(request, response);
            return;
        }
        //获取请求头中的令牌
        String requestHeader = request.getHeader(jwtProperties.getAdminTokenName());
        //判断令牌是否为空,如果为空则拦截
        if (!StringUtils.hasLength(requestHeader)) {
            log.info("当前请求头为空,拦截");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        //令牌不为空则解析令牌
        try {
            Claims claims = JwtUtils.parseJWT(jwtProperties.getAdminSecretKey(), requestHeader);
            //解析成功则将用户信息保存到当前线程的变量中
            Long empId = Long.valueOf(claims.get(EmplConstant.EMP_ID).toString());
            BaseContext.setCurrentId(empId);
            //放行
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            //解析失败则拦截
            log.error("解析令牌失败：{}", e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }
    }
}
