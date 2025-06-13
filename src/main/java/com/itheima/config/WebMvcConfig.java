package com.itheima.config;

import com.itheima.Filter.LoginCheckFilter;
import com.itheima.interceptor.JwtAdminInterceptor;
import com.itheima.json.JacksonObjectMapper;
import com.itheima.properties.FileProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 扩展spring MVC框架的消息转换器
     * @param converters
     */
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        log.info("扩展消息转换器...");
        //创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //需要为消息转换器设置一个对象转换器，对象转换器可以将Java对象序列化为json数据
        converter.setObjectMapper(new JacksonObjectMapper());
        //将自己的消息转化器加入容器中
        converters.add(0,converter);
    }

    /**
     * 静态资源映射
     */
    @Autowired
    private FileProperties fileProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射...");
        registry.addResourceHandler(fileProperties.getAccessUrl() + "**")
                .addResourceLocations("file:" + fileProperties.getUploadPath());
    }

    /**
     * 注册自定义拦截器
     */

    @Autowired
    private JwtAdminInterceptor jwtAdminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(jwtAdminInterceptor)
                .addPathPatterns("/emps/**")
                .addPathPatterns("/depts/**")
                .excludePathPatterns("/upload")
                .excludePathPatterns("/files/**")
                .excludePathPatterns("/login/**");
    }
//    @Bean //  将过滤器注册到spring容器中
//    public FilterRegistrationBean<LoginCheckFilter> loginCheckFilterRegistration(LoginCheckFilter loginCheckFilter) {
//
//        FilterRegistrationBean<LoginCheckFilter> registrationBean = new FilterRegistrationBean<>(loginCheckFilter);
//        registrationBean.setFilter(loginCheckFilter);
//        registrationBean.addUrlPatterns("/*"); //拦截所有请求
//        registrationBean.setOrder(1); //设置过滤器的优先级
//        return registrationBean; //返回注册对象
//    }
}
