package com.cdd.springboot.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Map;

//@SpringBootApplication
@Configuration
public class Application {
    @Bean
    public String getSpringBootApplication(ApplicationContext applicationContext) {
//        Test2 test2 = applicationContext.getBean(Test2.class);
//        Test3 test3 = applicationContext.getBean(Test3.class);
        String[] strings=applicationContext.getBeanNamesForAnnotation(Component.class);
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Test1.class);
//        test2.equals("1");
        return "strings[0]";
    }
}
