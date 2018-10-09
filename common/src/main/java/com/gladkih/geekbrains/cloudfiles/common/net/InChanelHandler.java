package com.gladkih.geekbrains.cloudfiles.common.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.io.Serializable;

public class InChanelHandler extends ChannelInboundHandlerAdapter { //(1)


    ListnerChanelHandler listnerChanelHandler;

    public InChanelHandler(ListnerChanelHandler listnerReceptionMessage) {
        this.listnerChanelHandler = listnerReceptionMessage;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("[CHANNEL ACTIVE] " +  this.hashCode());
        ctx.channel().closeFuture().addListener(f -> System.out.println("[CLOSE]"));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            System.out.println("[RECIVED MESSAGE]");
            if (msg != null){
                Serializable sendMess = listnerChanelHandler.onReceive((Serializable) msg);
                if (sendMess != null){
                    ctx.writeAndFlush(sendMess);
                }
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) throws Exception {
        throwable.printStackTrace();
        ctx.close();
        listnerChanelHandler.onExeption(throwable);
    }
}