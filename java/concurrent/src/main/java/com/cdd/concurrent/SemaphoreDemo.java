package com.cdd.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

/**
 * Semaphore信号灯
 *
 * @author cuoduidui
 * @date 2019-10-22 17:28
 **/
public class SemaphoreDemo {
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(100);
        Stream.iterate(1, i -> i++).limit(100).forEach(
                (i) -> {
                    service.submit(() -> {
                        try {
                            semaphore.acquire();
                            System.out.println("通过" + semaphore.availablePermits());
                            Thread.sleep(1000);
                            semaphore.release();
                            System.out.println("释放" + semaphore.availablePermits());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
        );
        service.shutdown();
//        Thread.sleep(Integer.MAX_VALUE);
    }
}
