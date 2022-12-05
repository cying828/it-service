package com.it.netty.chat;

import com.it.netty.handler.ChatClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @Author Cying
 * @Date 2022/7/26 10:39
 * @Description
 */
public class ChatClient1 {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup(1);

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder",new StringDecoder());
                            pipeline.addLast("encoder",new StringEncoder());
                            pipeline.addLast(new ChatClientHandler());

                        }
                    });

            ChannelFuture channelFuture = null;
            channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            Channel channel = channelFuture.channel();

            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                channel.writeAndFlush(line);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
          group.shutdownGracefully();
        }
    }
}
