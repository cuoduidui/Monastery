package com.cdd.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Indexed;

/**
 * 测试springbootapplication注解是否只能在启动类上
 * SpringBootApplication不一定非要注释在启动类上面
 * <p>
 * {@link AliasFor spring4.2开始提供的，可以把其他注解的属性指定到本注解上
 * {@link Indexed}Spring Framework 5.0 版本引入的注解 @Indexed，为 Spring 模式注解添加索引，以提升应用启动性能。
 * 当工程打包为 JAR 或在 IDE 工具中重新构建后，METE-INF/spring.components 文件将自动生成。换言之，该文件在编译时生成。
 * 当 Spring 应用上下文执行 @CompoentScan 扫描时，METE-INF/spring.components 将被 CandidateComponentsIndexLoader 读取并加载，转化为 CandidateComponentsIndex 对象，进而 @CompoentScan 不再扫描指定的 package，而是读取 CandidateComponentsIndex 对象，从而达到提升性能的目的。
 * @author cuoduidui
 * @date 2019-11-11 23:55
 **/
@SpringBootApplication
public class Test {
}
