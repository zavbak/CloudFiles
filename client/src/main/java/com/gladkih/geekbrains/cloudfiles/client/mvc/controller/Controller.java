package com.gladkih.geekbrains.cloudfiles.client.mvc.controller;

import com.gladkih.geekbrains.cloudfiles.client.mvc.view.View;
import com.gladkih.geekbrains.cloudfiles.common.net.ListnerChanelHandler;
import com.gladkih.geekbrains.cloudfiles.client.mvc.model.Model;

import io.reactivex.Completable;

import java.io.Serializable;

public class Controller implements ListnerChanelHandler {

    Model model;
    View view;

    public Controller(View view) {
        model = new Model(this);
        this.view = view;
    }

    @Override
    public Serializable onReceive(Serializable o) {
       return model.onReceive(o);
    }

    @Override
    public void exceptionNet(Throwable cause) {
        model.exceptionNet(cause);
    }

    public Completable sendMessage(Serializable serializable){
        return model.sendMessage(serializable);
    }

    public Completable connect(){
        return model.startNetClient();
    }

    public Completable disconect(){
        return model.desconnect();
    }

    public Completable sendAuth(){
       return model.sendAuth();
    }

    public void showMessage(Serializable o) {
        view.showMessage(o.toString());
    }
}
