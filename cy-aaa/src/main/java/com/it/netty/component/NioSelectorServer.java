package com.it.netty.component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author Cying
 * @Date 2022/7/25 16:01
 * @Description
 */
public class NioSelectorServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(9000));
        //设置为非阻塞
        serverSocket.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            //阻塞等待需要处理的事件
            selector.select();
            //获取selector注册的事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = server.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    int len = socketChannel.read(byteBuffer);
                    if (len>0) {
                        System.out.println("接收到消息："+new String(byteBuffer.array()));
                    }else if (len==-1) {
                        System.out.println("客户端断开连接");
                        socketChannel.close();
                    }

                }
                //删除key，防止下次重复处理
                iterator.remove();

            }

        }

    }
}
