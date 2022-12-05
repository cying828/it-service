package com.it.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

/**
 * @Author Cying
 * @Date 2022/7/26 10:38
 * @Description
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[ 客户端 ]"+channel.remoteAddress()+"上线了"+"\n");
        channelGroup.add(channel);
        System.out.println(channel.remoteAddress()+" 上线了 "+"\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[ 客户端 ]"+channel.remoteAddress()+" 下线了 "+"\n");
        System.out.println(channel.remoteAddress()+" 下线了 "+"\n");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (channel!=ch) {
                ch.writeAndFlush("[ 客户端 ]"+channel.remoteAddress()+" 发送了消息： "+msg+"\n");
            } else {
                ch.writeAndFlush("[ 自己 ]发送了消息"+msg+"\n");
            }
        });
    }
}
