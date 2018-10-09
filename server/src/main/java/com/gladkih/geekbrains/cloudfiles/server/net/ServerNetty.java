package com.gladkih.geekbrains.cloudfiles.server.net;


import com.gladkih.geekbrains.cloudfiles.common.net.InChanelHandler;
import com.gladkih.geekbrains.cloudfiles.common.net.Network;
import com.gladkih.geekbrains.cloudfiles.server.db.DBHelper;
import com.gladkih.geekbrains.cloudfiles.server.mvc.controller.Controller;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.reactivex.Completable;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class ServerNetty implements Network {


    private final int port;

    private static final int MAX_OBJ_SIZE = 1024 * 1024 * 100; // 10 mb
    private Channel channel;

    DBHelper dbHelper;

    public ServerNetty(int port) throws SQLException, ClassNotFoundException {
        this.port = port;
        this.dbHelper = DBHelper.getInstance();
    }


    @Override
    public Completable start() {

        return Completable.fromAction(() -> {
            EventLoopGroup mainGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            Network network = this;

            ServerBootstrap b = new ServerBootstrap();
            b
                    .group(mainGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new ObjectDecoder(MAX_OBJ_SIZE, ClassResolvers.cacheDisabled(null)),
                                    new ObjectEncoder(),
                                    new InChanelHandler(new Controller(network, dbHelper)));
                        }
                    });

            // connect
            System.out.println("Server start!");
            ChannelFuture f = b.bind().sync();

            // close
            f.channel().closeFuture().sync();

            //set chanel
            this.channel = f.channel();

            //before stop
            mainGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        });

    }

    @Override
    public Completable send(Serializable o) {
        return Completable.fromAction(() -> {
            channel.write(o);
            channel.flush();
        });
    }

    @Override
    public Completable close() {
        return Completable.fromAction(() -> {
            channel.close();
        });
    }


}
