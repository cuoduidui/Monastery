package com.cdd.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * 让一组线程到达一个屏障时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，
 * 所有被屏障拦截的线程才会继续干活。并且可以重置屏障限时数
 * 底层用到了ReentrantLock和Condition
 *
 * @author cuoduidui
 * @date 2019-10-23 14:20
 **/
public class CyclicBarrierDemo {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(20, () -> {
        System.out.println("哈哈，我先执行。");
    });

    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始");
        ExecutorService service = Executors.newFixedThreadPool(20);
        Stream.iterate(1, (i) -> i + 1).limit(50).forEach((a) -> {
            service.submit(() -> {
                try {
//                    if (a == 4) cyclicBarrier.reset();//重置
                    cyclicBarrier.await();//通知 到达屏障点  等待其他线程 如果达到目标数 则会万箭齐发
                    System.out.println("等待执行等待数" + cyclicBarrier.getNumberWaiting() + ",执行数：" + cyclicBarrier.getParties() + "当前线程id：" + a);
                    Thread.sleep(10000);
                    System.out.println("执行成功，等待数" + cyclicBarrier.getNumberWaiting() + ",执行数：" + cyclicBarrier.getParties() + "当前线程id：" + a);
//                    cyclicBarrier.reset();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        });
//        service.shutdown();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
