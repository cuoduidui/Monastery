package com.cdd.springboot;

import com.cdd.springboot.demo.Test1;
import com.cdd.springboot.demo.Test2;
import com.cdd.springboot.demo.Test3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.Annotation;

/**
 * 测试springbootapplication注解是否只能在启动类上
 * SpringBootApplication不一定非要注释在启动类上面
 * <p>
 * {@link AliasFor spring4.2开始提供的，可以把其他注解的属性指定到本注解上
 * {@link Indexed}Spring Framework 5.0 版本引入的注解 @Indexed，为 Spring 模式注解添加索引，以提升应用启动性能。
 * 当工程打包为 JAR 或在 IDE 工具中重新构建后，METE-INF/spring.components 文件将自动生成。换言之，该文件在编译时生成。
 * 当 Spring 应用上下文执行 @CompoentScan 扫描时，METE-INF/spring.components 将被 CandidateComponentsIndexLoader 读取并加载，
 * 转化为 CandidateComponentsIndex 对象，进而 @CompoentScan 不再扫描指定的 package，而是读取 CandidateComponentsIndex 对象，从而达到提升性能的目的。
 * {@link Configuration#proxyBeanMethods()}
 *
 * {@link Autowired Inject Resource}1、@Autowired是spring自带的，@Inject是JSR330规范实现的，@Resource是JSR250规范实现的，需要导入不同的包
 *
 * 2、@Autowired、@Inject用法基本一样，不同的是@Autowired有一个request属性
 *
 * 3、@Autowired、@Inject是默认按照类型匹配的，@Resource是按照名称匹配的
 *
 * 4、@Autowired如果需要按照名称匹配需要和@Qualifier一起使用，@Inject和@Name一起使用
 * @author cuoduidui
 * @date 2019-11-11 23:55
 **/
@SpringBootApplication
public class Test {
//    public static void main(String[] args) {
//        SpringApplication.run(Test.class, args);
////        Annotation[] annotations=Test3.class.getAnnotations();
////        System.out.println(annotations);
////
////        Annotation[] Test2annotations= Test2.class.getAnnotations();
////        System.out.println(Test2annotations);
//    }
}
