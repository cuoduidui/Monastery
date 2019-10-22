package com.cdd.io.io.nio;

import java.nio.ByteBuffer;

/**
 * describe:  只读缓冲区
 * 调用缓冲区的
 * asReadOnlyBuffer()方法， 将任何常规缓冲区转 换为只读缓冲区， 这个方法返回一个与原缓冲区完
 * 全相同的缓冲区， 并与原缓冲区共享数据， 只不过它是只读的。
 *
 * @author yangfengshan
 * @date 2018/12/05
 */
public class ReadOnlyBufferTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            System.out.println(byteBuffer.put((byte) i));
        }
        //创建0到9的数据流 也可以这样IntStream.rangeClosed(0,10)
//        IntStream.iterate(0,n->n++).limit(10).forEach((b)->byteBuffer.put((byte) b));
        ByteBuffer readBuffer = byteBuffer.asReadOnlyBuffer();
        readBuffer.flip();
        //会报错java.nio.ReadOnlyBufferException
        //readBuffer.put((byte)1);
        byteBuffer.put(0, (byte) 100);
        for (int i = 0; i < readBuffer.capacity(); i++) {
            System.out.println(readBuffer.get());
        }
    }
}
