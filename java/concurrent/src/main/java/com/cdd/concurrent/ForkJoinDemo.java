package com.cdd.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * describe:
 * 继承这两个类中的一个
 * 1. RecursiveAction，用于没有返回结果的任务
 * <p>
 * 2. RecursiveTask，用于有返回值的任务
 *
 * @author yangfengshan
 * @date 2018/11/29
 */
public class ForkJoinDemo {

    public static void main(String[] args) {
        List<Long> elementList = Arrays.asList(1L, 2L, 6L, 2L, 5L, 7L, 2L, 1L, 8L, 9L, 3L, 1L, 3L, 6L, 23L, 12L, 41L, 51L, 56L, 87L);
        LongAccumulator accumulator = new LongAccumulator(((left, right) -> left + right), 0);
        SumForkJoinTask sumForkJoinTask = new SumForkJoinTask(elementList, accumulator);
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        //ForkJoinPool 默认线程数量为  Runtime.getRuntime().availableProcessors()
        System.out.println(Runtime.getRuntime().availableProcessors());
        forkJoinPool.invoke(sumForkJoinTask);
        System.out.println(forkJoinPool.getPoolSize());//返回已启动但尚未终止的工作线程数。
        System.out.println(accumulator.get());
    }

    static class SumForkJoinTask extends RecursiveAction {
        private List<Long> elements;
        private LongAccumulator accumulator;

        public SumForkJoinTask(List<Long> elements, LongAccumulator accumulator) {
            this.accumulator = accumulator;
            this.elements = elements;
        }

        @Override
        protected void compute() {
            if (elements.isEmpty()) {
                return;
            }
            int size = elements.size();
            int index = size / 2;
            if (size > 1) {
                List<Long> left = elements.subList(0, index);
                List<Long> right = elements.subList(index, size);
                SumForkJoinTask leftTask = new SumForkJoinTask(left, accumulator);
                SumForkJoinTask rightTask = new SumForkJoinTask(right, accumulator);
                System.out.println("分割成两个线程" + Thread.currentThread().getId());
                leftTask.fork().join();
                rightTask.fork().join();
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                accumulator.accumulate(elements.get(0));
            }
        }
    }
}
