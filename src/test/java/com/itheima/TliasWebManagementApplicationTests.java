package com.itheima;

import com.example.HeaderGenerator;
import com.example.HeaderParser;
import com.example.TokenParser;
import com.itheima.controller.DeptController;
import com.itheima.service.DeptService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;

import javax.xml.transform.sax.SAXResult;


@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private ApplicationContext  applicationContext;
    @Test
    public void test01(){
        //根据bean的名称获取bean对象
        DeptController deptController = (DeptController) applicationContext.getBean("deptController");
        System.out.println(deptController);

        //根据bean的类型获取bean对象
        DeptController deptController1 = applicationContext.getBean(DeptController.class);
        System.out.println(deptController1);

        //根据bean的名称 及 类型获取bean对象
        DeptController deptController2 = applicationContext.getBean("deptController", DeptController.class);
        System.out.println(deptController2);

    }

    @Test
    public void test02(){
        for (int i = 0; i < 10; i++) {
            DeptController bean = applicationContext.getBean(DeptController.class);
            System.out.println("第"+(i+1)+"次获取bean对象:"+bean);
        }
    }

    @Autowired
    private SAXReader saxReader;

    @Test
    public void test03() throws DocumentException {

//            // 创建 SAXReader 对象
//            SAXReader saxReader = new SAXReader();
        SAXReader bean = applicationContext.getBean(SAXReader.class);
        System.out.println("bean对象:"+bean);

        // 读取 XML 文件
            Document document = saxReader.read(this.getClass().getClassLoader().getResource("1.xml"));

            // 获取根元素
            Element rootElement = document.getRootElement();

            // 获取 name 和 age 元素的文本内容
            String name = rootElement.element("name").getText();
            String age = rootElement.element("age").getText();

            // 打印结果
            System.out.println(name + " : " + age);
    }

    @Test
    public void test04(){
//        TokenParser bean = applicationContext.getBean(TokenParser.class);
        HeaderParser bean = applicationContext.getBean(HeaderParser.class);
        System.out.println("bean:"+bean);
        HeaderGenerator bean1 = applicationContext.getBean(HeaderGenerator.class);
        System.out.println("bean1:"+bean1);
    }

}
