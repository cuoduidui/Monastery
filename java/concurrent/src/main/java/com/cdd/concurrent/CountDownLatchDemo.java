package com.cdd.concurrent;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * CyclicBarrier  用到aqs
 * 当到达的线程到达一定量才会放开执行
 *count 递减 只能用一次
 * @author cuoduidui
 * @date 2019-10-23 16:58
 **/
public class CountDownLatchDemo {
    private static CountDownLatch downLatch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Stream.iterate(1, (i) -> i + 1).limit(100).forEach((i) -> {
                    service.submit(() -> {
                                try {
                                    downLatch.countDown();//增加计数器
                                    System.out.println("到达一个线程:" + i + "," + new Date().getTime());
                                    downLatch.await();

                                    System.out.println("释放一个线程:" + i + "," + new Date().getTime());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                }
        );
        service.shutdown();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
