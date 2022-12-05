package com.it.netty.component;

import com.it.netty.handler.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Author Cying
 * @Date 2022/7/25 18:45
 * @Description
 */
public class NettyServer {
    public static void main(String[] args) {

        //两个线程池，bossGroup处理连接请求，workerGroup真正和客户端进行业务处理
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);



        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)//设置两个线程组
                    //使用NioServerSocketChannel 作为服务器通道实现
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            //启动服务器，并绑定端口，bind是异步操作，sync方法是等待异步方法执行完毕
            ChannelFuture cf = bootstrap.bind(9000).sync();
            //等待服务端监听端口关闭
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
