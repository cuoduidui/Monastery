package com.cdd.springboot;

import com.cdd.springboot.demo.Test1;
import com.cdd.springboot.demo.Test2;
import com.cdd.springboot.demo.Test3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationFilter;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.RepeatableContainers;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cuoduidui
 * @date 2020-07-01 11:27
 **/
//@EnableAutoConfiguration
public class RunTest extends Test {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Test.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run();
//        SpringApplication.run(Test.class, args);
//        Annotation[]annotations=Test3.class.getAnnotations();
//        System.out.println(annotations);
//
//        Annotation[] Test2annotations= Test2.class.getAnnotations();
//        System.out.println(springApplication);
        Annotation[] annotations = Test.class.getAnnotations();
        Annotation[] annotations1 = RunTest.class.getAnnotations();
        System.out.println(Arrays.stream(annotations).map(annotation -> annotation.toString()).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(annotations1).map(annotation -> annotation.toString()).collect(Collectors.joining(",")));
//        MergedAnnotations mergedAnnotations=MergedAnnotations.from(RunTest.class, MergedAnnotations.SearchStrategy.DIRECT, RepeatableContainers.none(), AnnotationFilter.NONE);
//        MergedAnnotations mergedAnnotations1=MergedAnnotations.from(Test.class, MergedAnnotations.SearchStrategy.DIRECT, RepeatableContainers.none(), AnnotationFilter.NONE);
//        System.out.println(mergedAnnotations);
//        System.out.println(mergedAnnotations1);
//        System.out.println(Arrays.stream(annotations).map(annotation -> annotation.toString()).collect(Collectors.joining(",")));
//        System.out.println(Arrays.stream(annotations1).map(annotation -> annotation.toString()).collect(Collectors.joining(",")));

    }

}
