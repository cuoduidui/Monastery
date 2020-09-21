package com.cdd.springboot.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.cdd.springboot.demo", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Test1.class), useDefaultFilters = false)
public class Application {
    //扫描自定义注解加入到spring bean容器中
//    @Bean
//    public String getSpringBootApplication(ApplicationContext applicationContext) {
////        Test2 test2 = applicationContext.getBean(Test2.class);
////        Test3 test3 = applicationContext.getBean(Test3.class);
////        String[] strings = applicationContext.getBeanNamesForAnnotation(Component.class);
////        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Test1.class);
//////        test2.equals("1");
////        Annotation[] annotations = Test3.class.getAnnotations();
////        String test1Beans = beans.keySet().stream().collect(Collectors.joining(","));
////        System.out.println(test1Beans);
//        return "1";
//    }
}
