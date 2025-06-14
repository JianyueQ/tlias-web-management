package com.itheima.config;

import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class SAXReaderConfig {

    //声明第三放bean对象
    @Bean   //将当前方法的返回值，作为bean对象存入spring容器中
            //通过@Bean注解的name/value属性，指定bean的名称,如果未指定，则默认为方法名
    public SAXReader saxReader(){
        return new SAXReader();
    }
}
