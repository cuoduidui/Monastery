package com.cdd.springboot.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.stream.Collectors;

//@SpringBootApplication
@Configuration
public class Application {
    @Bean
    public String getSpringBootApplication(ApplicationContext applicationContext) {
        Test2 test2 = applicationContext.getBean(Test2.class);
        Test3 test3 = applicationContext.getBean(Test3.class);
        String[] strings = applicationContext.getBeanNamesForAnnotation(Component.class);
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Test1.class);
//        test2.equals("1");
        Annotation[] annotations = Test3.class.getAnnotations();
        String test1Beans = beans.keySet().stream().collect(Collectors.joining(","));
        System.out.println(test1Beans);
        return test1Beans;
    }
}
