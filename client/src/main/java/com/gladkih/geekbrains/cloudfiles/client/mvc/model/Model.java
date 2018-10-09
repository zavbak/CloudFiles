package com.gladkih.geekbrains.cloudfiles.client.mvc.model;


import com.gladkih.geekbrains.cloudfiles.client.mvc.controller.Controller;
import com.gladkih.geekbrains.cloudfiles.client.net.ClientNetty;

import com.gladkih.geekbrains.cloudfiles.common.command.AuthCommReq;
import com.gladkih.geekbrains.cloudfiles.common.net.ListnerChanelHandler;
import com.gladkih.geekbrains.cloudfiles.common.net.Network;


import io.reactivex.Completable;

import java.io.Serializable;

public class Model implements ListnerChanelHandler {

    private final Network network;
    private final Controller controller;
    private EnumState state;

    /**
     *
     * @param controller
     */
    public Model(Controller controller) {
        this.controller = controller;
        this.network = new ClientNetty(controller, getHost(), getPort());
        this.state = EnumState.disconnect;

    }

    private String getHost() {
        return "localhost";
    }

    private int getPort() {
        return 8189;
    }

    private AuthCommReq getAuthComm() {
        return new AuthCommReq("test1", "password".hashCode());
    }

    @Override
    public Serializable onReceive(Serializable o) {
        controller.showMessage(o);
        return null;
    }

    @Override
    public void exceptionNet(Throwable throwable) {
        state = EnumState.disconnect;
    }

    public Completable sendMessage(Serializable serializable) {
        return network.send(serializable);
    }

    public Completable startNetClient() {
        return network.start();
    }

    public Completable desconnect() {
        return network.close();
    }

    public Completable sendAuth() {
        return network.send(getAuthComm())
                .doOnComplete(() -> {
                    state = EnumState.waitingAuth;
                });
    }


}
