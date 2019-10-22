package com.cdd.io.io.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * describe: 观察各个操作后buffer三种状态值的变化
 *
 * @author yangfengshan
 * @date 2018/12/05
 */
public class BufferValueOnChange {
    public static void main(String[] args) throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/README.md")) {
            //初始化   allocate()相当于创建了一个指定大小的数组， 并把它包装为缓冲区对象其实也可以自己定义一个数组充当缓冲区
//            byte[] bytes=new byte[10];
//            ByteBuffer byteBuffer=ByteBuffer.wrap(bytes);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            outPrintln("初始化", byteBuffer);
            FileChannel fileChannel = fileInputStream.getChannel();
            //读取文件
            fileChannel.read(byteBuffer);
            outPrintln("读取文件red", byteBuffer);
            byteBuffer.flip();
            outPrintln("调用flip", byteBuffer);
            while (byteBuffer.remaining() > 0) {
                byteBuffer.get();
            }
            outPrintln("调用get", byteBuffer);
            byteBuffer.clear();
            outPrintln("调用clear", byteBuffer);
//          初始化---limit: 1024 ,position : 0,capacity: 1024
//          读取文件red---limit: 1024 ,position : 51,capacity: 1024
//          调用flip---limit: 51 ,position : 0,capacity: 1024
//          调用get---limit: 51 ,position : 51,capacity: 1024
//          调用clear---limit: 1024 ,position : 0,capacity: 1024
        }
    }

    private static void outPrintln(String change, ByteBuffer byteBuffer) {
        System.out.println(change + "---" + String.format("limit: %s ,position : %s,capacity: %s,mark: %s", byteBuffer.limit(), byteBuffer.position(), byteBuffer.capacity(), byteBuffer.mark()));
    }
}
