package com.cdd.springboot.demo;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author cuoduidui
 * @date 2019-11-13 17:32
 * 1、没有被@Inherited注解标记的注解，不具有继承特性，在子类中获取不到父类的注解；
 * 2、注解继承,它只作用于子类与父类之间的继承，对于接口之间的继承和类与接口之间的实现，这两种继承关系不起作用；
 * 3、注解标记的注解，在使用时，如果父类和子类都使用的注解是同一个，那么子类的注解会覆盖父类的注解。
 **/
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Test1 {
}
