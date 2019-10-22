package com.cdd.concurrent;

import java.util.stream.IntStream;

public class DaemonThreadQuestion {

    public static void main(String[] args) throws InterruptedException {
        // main 线程
        Thread t1 = new Thread(() -> {
            System.out.println("Hello,World");
            Thread currentThread = Thread.currentThread();
            System.out.printf("线程[name : %s, daemon:%s]: Hello,World\n",
                    currentThread.getName(),
                    currentThread.isDaemon()
            );
        }, "daemon");
        // 编程守候线程
        t1.setDaemon(true);
        t1.start();
        Thread.sleep(Integer.MAX_VALUE);
        // 守候线程的执行依赖于执行时间（非唯一评判）
    }
}
