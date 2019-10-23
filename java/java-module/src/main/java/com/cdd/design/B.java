package com.cdd.design;

/**
 * @author cuoduidui
 * @date 2019-10-23 17:29
 **/
public class B extends A {
    public static void main(String[] args) {
        //静态方法 静态属性 属于类的本身 所以子类不会继承父类的静态属性和方法 但是可以使用
        //子类中有与父类相同的静态属性和方法时  互不影响
        B b = new B();

    }
}
