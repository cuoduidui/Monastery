package com.cdd.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.AliasFor;

/**
 * 测试springbootapplication注解是否只能在启动类上
 * SpringBootApplication不一定非要注释在启动类上面
 * <p>
 * {@link AliasFor spring4.2开始提供的，可以把其他注解的属性指定到本注解上
 *
 * @author cuoduidui
 * @date 2019-11-11 23:55
 **/
@SpringBootApplication
public class Test {
}
