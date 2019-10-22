package com.cdd.io.io.nio;

import java.nio.ByteBuffer;

/**
 * describe:  缓存分片
 * 现有缓冲区上切出一片来作为一个新的缓冲区， 但现有的缓冲区与创建的子缓冲区在底层
 * 数组层面上是数据共享的
 *
 * @author yangfengshan
 * @date 2018/12/05
 */
public class BufferSlice {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte) i);
        }
        //创建子缓冲区
        byteBuffer.position(8);
        byteBuffer.limit(12);
        ByteBuffer slice = byteBuffer.slice();
        // 改变子缓冲区的内容
        for (int i = 0; i < slice.capacity(); ++i) {
            byte b = slice.get(i);
            b *= 10;
            slice.put(i, b);
        }
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());
        while (byteBuffer.remaining() > 0) {
            System.out.println(byteBuffer.get());
        }
    }
}
