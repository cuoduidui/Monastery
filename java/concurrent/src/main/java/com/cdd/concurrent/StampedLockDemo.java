package com.cdd.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

/**
 * describe:  票据锁：获取锁的时候得到一个票据 如果是读操作第一次不加锁读 读完返回一个票据
 * 判断两次的票据是否一样 如果不一致 则加锁读
 * <p>
 * CLH锁的基本思想如下:锁维护一个等待线程队列,所有申请锁,但是没有成功的线程都记录在这个队列中,每一个节点代表一个线程,保存一个标记位(locked).
 * 用与判断当前线程是否已经释放锁;locked=true 没有获取到锁,false 已经成功释放了锁
 * 当一个线程视图获得锁时,取得等待队列的尾部节点作为其前序节点.并使用类似如下代码判断前序节点是否已经成功释放锁:
 * 只要前序节点(pred)没有释放锁,则表示当前线程还不能继续执行,因此会自旋等待,
 * 反之,如果前序线程已经释放锁,则当前线程可以继续执行.
 * 释放锁时,也遵循这个逻辑,线程会将自身节点的locked位置标记位false,那么后续等待的线程就能继续执行了
 * StampedLock的等待队列与RRW的CLH队列相比，有以下特点：
 *
 * 当入队一个线程时，如果队尾是读结点，不会直接链接到队尾，而是链接到该读结点的cowait链中，cowait链本质是一个栈；
 * 当入队一个线程时，如果队尾是写结点，则直接链接到队尾；
 * 唤醒线程的规则和AQS类似，都是首先唤醒队首结点。区别是StampedLock中，当唤醒的结点是读结点时，会唤醒该读结点的cowait链中的所有读结点（顺序和入栈顺序相反，也就是后进先出）。
 * 另外，StampedLock使用时要特别小心，避免锁重入的操作，在使用乐观读锁时也需要遵循相应的调用模板，防止出现数据不一致的问题。
 * https://segmentfault.com/a/1190000015808032?utm_source=tag-newest
 * StampedLock的主要特点概括一下，有以下几点：
 * <p>
 * 所有获取锁的方法，都返回一个邮戳（Stamp），Stamp为0表示获取失败，其余都表示成功；
 * 所有释放锁的方法，都需要一个邮戳（Stamp），这个Stamp必须是和成功获取锁时得到的Stamp一致；
 * StampedLock是不可重入的；（如果一个线程已经持有了写锁，再去获取写锁的话就会造成死锁）
 * StampedLock有三种访问模式：
 * ①Reading（读模式）：功能和ReentrantReadWriteLock的读锁类似
 * ②Writing（写模式）：功能和ReentrantReadWriteLock的写锁类似
 * ③Optimistic reading（乐观读模式）：这是一种优化的读模式。
 * StampedLock支持读锁和写锁的相互转换
 * 我们知道RRW中，当线程获取到写锁后，可以降级为读锁，但是读锁是不能直接升级为写锁的。
 * StampedLock提供了读锁和写锁相互转换的功能，使得该类支持更多的应用场景。
 * 无论写锁还是读锁，都不支持Conditon等待
 *
 * @author yangfengshan
 * @date 2018/11/28
 */
public class StampedLockDemo {
    private double x, y;
    private final StampedLock sl = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        StampedLockDemo stampedLockDemo = new StampedLockDemo();
        //1）newFixedThreadPool和newSingleThreadExecutor:
//          主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
//        2）newCachedThreadPool和newScheduledThreadPool:
//          主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                stampedLockDemo.move(2, 3);
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            System.out.println(String.format("x:[%s]y[%s]", stampedLockDemo.x, stampedLockDemo.y));
        }


    }

    void move(double deltaX, double deltaY) {
        long stamp = sl.writeLock();
        try {
            Thread.sleep(100);
            x += deltaX;
            y += deltaY;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin() { // A read-only method
        long stamp = sl.tryOptimisticRead();//乐观读模式
        double currentX = x, currentY = y;
        if (!sl.validate(stamp)) { //判断票据是否发生变化  如果发生变化则进行悲观读
            stamp = sl.readLock(); //悲观读
            try {
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    void moveIfAtOrigin(double newX, double newY) { // upgrade
        // Could instead start with optimistic, not read mode
        long stamp = sl.readLock();   //悲观读
        try {
            while (x == 0.0 && y == 0.0) {     //循环，检查当前状态是否符合
                long ws = sl.tryConvertToWriteLock(stamp);     //将读锁转为写锁
                if (ws != 0L) {
                    stamp = ws;//如果成功 替换票据
                    x = newX;
                    y = newY;
                    break;
                } else {     //如果不能成功转换为写锁
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);
        }
    }
}
