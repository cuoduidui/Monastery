package com.cdd.io.io.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * describe:  映射io
 * 内存映射文件 I/O 是一种读和写文件数据的方法，它可以比常规的基于流或者基于通道的 I/O 快的多。
 * 内存映射文件 I/O 是通过使文件中的数据出现为 内存数组的内容来完成的， 这其初听起来似乎不过
 * 就是将整个文件读到内存中， 但是事实上并不是这样。 一般来说， 只有文件中实际读取或者写入的部
 * 分才会映射到内存中
 *
 * @author yangfengshan
 * @date 2018/12/06
 */
public class MappingBuffer {

    public static void main(String[] args) throws Exception {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(System.getProperty("user.dir") + "/README.md", "rw")) {

            FileChannel fileChannel = randomAccessFile.getChannel();
            MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
            byteBuffer.put(0, (byte) 1);
            byteBuffer.put(10, (byte) 1);
        }
    }
}
