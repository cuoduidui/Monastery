package com.cdd.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 哲学家进餐问题
 * https://leetcode-cn.com/problems/the-dining-philosophers/
 *
 * @author cuoduidui
 * @date 2020-05-09 16:26
 **/
public class DiningPhilosophers {
    private static volatile long[] x = new long[]{2, 4, 8, 16, 32};
    private static long z = 0;
    private static volatile int y = 0;
    private static Semaphore semaphore = new Semaphore(4);

    public DiningPhilosophers() {

    }

    public static void main(String[] args) {
        System.out.println(DiningPhilosophers.x);
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        if ((x[philosopher] | z) > 0) return;
        if ((x[philosopher + 1 % 5] | z) == 1) return;
        semaphore.acquire();
        x[philosopher] = 0;
        x[philosopher + 1 % 5] = 0;
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        semaphore.release();
    }
}
