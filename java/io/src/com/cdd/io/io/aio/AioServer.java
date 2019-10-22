package com.cdd.io.io.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * aio server 练习
 *
 * @author cdd
 * @create 2018-12-18 18:32
 **/
public class AioServer {
    private final int port;

    public static void main(String[] args) throws Exception {
        new AioServer(8080);
    }

    public AioServer(int port) throws Exception {
        this.port = port;
        listen();
    }

    private void listen() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        AsynchronousChannelGroup asynchronousChannelGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
        final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(asynchronousChannelGroup);
        serverSocketChannel.bind(new InetSocketAddress(this.port));
        System.out.println("绑定端口：" + this.port);
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                System.out.println("IO 操作成功， 开始获取数据");
                try {
                    byteBuffer.clear();
                    result.read(byteBuffer).get();
                    byteBuffer.flip();
                    result.write(byteBuffer);
                    byteBuffer.flip();
                } catch (Exception e) {
                    System.out.println(e.toString());
                } finally {
                    try {
                        result.close();
                        serverSocketChannel.accept(null, this);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
                System.out.println("操作完成");
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("失败");
            }
        });
        Thread.sleep(Integer.MAX_VALUE);
    }
}
