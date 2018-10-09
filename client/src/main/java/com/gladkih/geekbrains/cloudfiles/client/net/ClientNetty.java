package com.gladkih.geekbrains.cloudfiles.client.net;

import com.gladkih.geekbrains.cloudfiles.client.mvc.controller.Controller;
import com.gladkih.geekbrains.cloudfiles.common.net.InChanelHandler;
import com.gladkih.geekbrains.cloudfiles.common.net.ListnerChanelHandler;
import com.gladkih.geekbrains.cloudfiles.common.net.Network;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.reactivex.Completable;

import java.io.Serializable;

public class ClientNetty implements Network {

    private static final int MAX_OBJ_SIZE = 1024 * 1024 * 100; // 10 mb
    private Channel channel;

    private EventLoopGroup workerGroup;
    private String host;
    private int port;

    ListnerChanelHandler listnerChanelHandler;

    public ClientNetty(ListnerChanelHandler listnerChanelHandler, String host, int port) {
        this.host = host;
        this.port = port;
        this.listnerChanelHandler = listnerChanelHandler;
    }

    @Override
    public Completable connect() {

        return Completable.fromAction(() -> {

            if (channel != null){
                if (channel.isOpen()){
                    return;
                }
            }
            workerGroup = new NioEventLoopGroup();

            Bootstrap b = new Bootstrap();                    // (1)
            b.group(workerGroup);                             // (2)
            b.channel(NioSocketChannel.class);                // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            new ObjectDecoder(MAX_OBJ_SIZE, ClassResolvers.cacheDisabled(null)),
                            new ObjectEncoder(),
                            new InChanelHandler(listnerChanelHandler)
                    );
                }
            });


            ChannelFuture future = b.connect(host, port);
            channel = future.channel();
            future.sync();
        });
    }

    @Override
    public Completable send(Serializable o) {
        return Completable.fromAction(() -> {
            channel.writeAndFlush(o);
        });
    }

    @Override
    public Completable close() {
        return Completable.fromAction(() -> {
            channel.close().sync();
            workerGroup.shutdownGracefully();
        });
    }
}
