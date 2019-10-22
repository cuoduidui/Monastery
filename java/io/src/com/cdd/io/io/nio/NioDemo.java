package com.cdd.io.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO练习
 *
 * @author cdd
 * @create 2018-12-17 20:10
 **/
public class NioDemo {

    public Selector getServerSelector() throws IOException {
        //创建selector
        Selector selector = Selector.open();
        //创建通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //打开socket
        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(9090);
        //绑定地址
        serverSocket.bind(address);
        //向selector注册
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        return selector;
    }

    public Selector getClientSelector() throws IOException {
        //创建selector
        Selector selector = Selector.open();
        //创建通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞模式
        socketChannel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress(9090);
        //向selector注册
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        //连接
        socketChannel.connect(address);
        return selector;
    }

    public void listen(Selector selector) throws Exception {
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeySet.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                keyIterator.remove();
                process(selectionKey, selector);
            }
        }
    }

    public void process(SelectionKey selectionKey, Selector selector) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            int len = socketChannel.read(byteBuffer);

            if (len > 0) {
                byteBuffer.flip();
                String msg = new String(byteBuffer.array(), 0, len);
                System.out.println(msg);
                SelectionKey selectionKey1 = socketChannel.register(selector, SelectionKey.OP_WRITE);
                selectionKey.attach(msg);//将给定的对象附加到此键。
            } else {
                socketChannel.close();
            }
            byteBuffer.clear();
        } else if (selectionKey.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            String msg = (String) selectionKey.attachment();//检索当前附件
            ByteBuffer block = ByteBuffer.wrap(("Hello " + msg).getBytes());
            if (block != null) {
                socketChannel.write(block);
            } else {
                socketChannel.close();
            }
        } else if (selectionKey.isConnectable()) {
            // 是否可连接
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            // 完成连接
            if (socketChannel.isConnectionPending()) {
                socketChannel.finishConnect();
                System.out.println("连接成功...");
                // 发送数据给Server
                String message_to_server = "Hello,Server...";
                byteBuffer.clear();
                byteBuffer.put(message_to_server.getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                System.out.println("Client发送的数据:" + message_to_server);
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else {
                System.exit(1); // 连接失败 退出
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            NioDemo nioDemo = new NioDemo();
            try {
                nioDemo.listen(nioDemo.getServerSelector());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            NioDemo nioDemo = new NioDemo();
            try {
                nioDemo.listen(nioDemo.getClientSelector());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
