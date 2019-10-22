package com.cdd.io.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * describe: 直接缓冲区 堆外内存
 * 直接缓冲区是为加快 I/O 速度， 使用一种特殊方式为其分配内存的缓冲区
 *
 * @author yangfengshan
 * @date 2018/12/06
 */
public class DirectBuffer {
    public static void main(String[] args) throws Exception {
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try (FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/README.md");
             FileOutputStream fileOutputStream1 = new FileOutputStream("d:/test1.txt");
             FileOutputStream fileOutputStream2 = new FileOutputStream("d:/test2.txt")) {
            FileChannel inputChannel = fileInputStream.getChannel();
            FileChannel outputChannel1 = fileOutputStream1.getChannel();
            FileChannel outoutChannel2 = fileOutputStream2.getChannel();
            long start1 = System.currentTimeMillis();
            while (true) {
                directBuffer.clear();
                int r = inputChannel.read(directBuffer);
                if (r == -1) {
                    break;
                }
                directBuffer.flip();
                outputChannel1.write(directBuffer);
            }
            System.out.println(String.format("直接内存需要消耗的时间：%s", System.currentTimeMillis() - start1));
            long start2 = System.currentTimeMillis();
            while (true) {
                byteBuffer.clear();
                int r = inputChannel.read(byteBuffer);
                if (r == -1) {
                    break;
                }
                byteBuffer.flip();
                outoutChannel2.write(byteBuffer);
            }
            System.out.println(String.format("堆内存需要消耗的时间：%s", System.currentTimeMillis() - start2));
        }
    }
}
