package com.itheima;

import com.example.EnableHeaderConfig;
import com.example.HeaderParser;
import com.example.MyImportSelector;
import com.example.TokenParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@ComponentScan({"com.itheima", "com.example"})  //  指定多个组件扫描路径
//@Import({TokenParser.class}) //  导入普通类,交给IOC容器管理
//@Import({HeaderParser.class}) //  导入配置类,交给IOC容器管理
//@Import({MyImportSelector.class})  //导入ImportSelector实现类
@EnableHeaderConfig
public class TliasWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebManagementApplication.class, args);
    }

}
