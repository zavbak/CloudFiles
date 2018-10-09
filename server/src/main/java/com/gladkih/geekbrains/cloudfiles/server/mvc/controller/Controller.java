package com.gladkih.geekbrains.cloudfiles.server.mvc.controller;

import com.gladkih.geekbrains.cloudfiles.common.net.ListnerChanelHandler;
import com.gladkih.geekbrains.cloudfiles.common.net.Network;
import com.gladkih.geekbrains.cloudfiles.server.db.DBHelper;
import com.gladkih.geekbrains.cloudfiles.server.mvc.model.Model;
import io.reactivex.Completable;

import java.io.Serializable;
import java.sql.SQLException;

public class Controller implements ListnerChanelHandler {

    Model model;

    public Controller(Network network, DBHelper dbHelper) throws SQLException, ClassNotFoundException {
        model = new Model(network,dbHelper);
    }

    @Override
    public Serializable onReceive(Serializable o) {
       return model.onReceive(o);
    }

    @Override
    public void exceptionNet(Throwable throwable) {
        model.exceptionNet(throwable);
    }

    public Completable sendMessaga(Serializable serializable){
        return model.sendMessaga(serializable);
    }
}
