package com.it.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author Cying
 * @Date 2022/7/26 10:39
 * @Description
 */
public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println(msg.trim());

    }
}
