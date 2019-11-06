package com.cdd.concurrent;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture demo
 *
 * @author cuoduidui
 * @date 2019-11-06 14:12
 **/
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("开始执行！");
        }).thenRun(() -> {
            System.out.println("开始执行中");
        });
        Thread.sleep(Integer.MAX_VALUE);
    }
}
