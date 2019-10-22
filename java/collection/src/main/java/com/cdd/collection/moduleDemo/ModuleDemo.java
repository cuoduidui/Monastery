package com.cdd.collection.moduleDemo;

//import com.cdd.design.Design;

import java.io.IOException;
import java.lang.module.ModuleDescriptor;
import java.util.logging.Logger;

/**
 * @author yangfengshan
 * @create 2019-08-22 15:30
 **/
public class ModuleDemo {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger("ModuleDemo");
        //引用不到
//        Design design=new Design();
        ModuleDemo moduleDemo = new ModuleDemo();
        java.lang.Module module = moduleDemo.getClass().getModule();
        System.out.println(module.getName());
        ModuleDescriptor descriptor = module.getDescriptor();
        descriptor.packages().forEach(s -> {
            System.out.printf("packages:%s \n", s);
            logger.info("logger");
        });
        descriptor.modifiers().forEach(s -> {
            System.out.printf("modifiers:%s \n", s);
        });
        descriptor.provides().forEach(System.out::println);
        descriptor.requires().forEach(s -> {
            System.out.printf("requires:%s \n", s);
        });
        descriptor.exports().forEach(s -> {
            System.out.printf("exports:%s \n", s);
        });
        descriptor.opens().forEach(s -> {
            System.out.printf("opens:%s \n", s);
        });
        var moduleDemo1=new ModuleDemo();
    }
}
