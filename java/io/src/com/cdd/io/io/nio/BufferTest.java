package com.cdd.io.io.nio;

import java.nio.IntBuffer;

/**
 * describe:  Buffer 练习
 * position： 指定了下一个将要被写入或者读取的元素索引， 它的值由 get()/put()方法自动更新， 在
 * 新创建一个 Buffer 对象时， position 被初始化为 0。
 * limit： 指定还有多少数据需要取出(在从缓冲区写入通道时)， 或者还有多少空间可以放入数据(在从
 * 通道读入缓冲区时)。
 * capacity： 指定了可以存储在缓冲区中的最大数据容量， 实际上， 它指定了底层数组的大小， 或者至
 * 少是指定了准许我们使用的底层数组的容量。
 * maker：初始值为-1，用于备份当前的position;
 * 三者之间的关系 0 <= position <= limit <= capacity
 * https://www.jianshu.com/p/052035037297
 *
 * @author yangfengshan
 * @date 2018/12/05
 */
public class BufferTest {
    public static void main(String[] args) {
        //新缓冲区的当前位置将为零， 其界限(限制位置)将为其容量。 它将具有一个底层实现数组，
        //其数组偏移量将为零
        //分配的是堆内存   直接内存分配调用
        //堆外内存通过ByteBuffer.allocateDirect(100); 申请  详情：https://www.jianshu.com/p/35cf0f348275
        IntBuffer intBuffer = IntBuffer.allocate(128);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put((i + 1) * (intBuffer.position() + 1));
        }
        //重置位置
        //读取数据前必须flip
        //必须调用 flip()方法， 该方法将会完成两件事情：
        //1. 把 limit 设置为当前的 position 值  保证读取完正好读到最后一个字符
        //2. 把 position 设置为 0 保证能从第一个字符读
        intBuffer.flip();
        //读取所有的值
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.position() + ":" + intBuffer.get());
        }
    }
}
