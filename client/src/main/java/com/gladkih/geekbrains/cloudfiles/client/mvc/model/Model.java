package com.gladkih.geekbrains.cloudfiles.client.mvc.model;


import com.gladkih.geekbrains.cloudfiles.client.mvc.controller.Controller;
import com.gladkih.geekbrains.cloudfiles.client.net.ClientNetty;

import com.gladkih.geekbrains.cloudfiles.common.net.ListnerChanelHandler;
import com.gladkih.geekbrains.cloudfiles.common.net.Network;


import io.reactivex.Completable;

import java.io.Serializable;

public class Model  {

    private final Controller controller;
    private final HandlerModelCommand handlerCommand;


    private boolean isConnect  = false;
    private boolean isAccessed = false;


    public Model(Controller controller) {
        this.controller = controller;
        this.handlerCommand = new HandlerModelCommand(this);
    }

    public void start(){
        handlerCommand.start();
    }

    public void showMessage(String mess){
        controller.showMessage(mess);
    }

    public void sendString(String str) {
        handlerCommand.sendString(str);
    }

    public void connect() {
        handlerCommand.connect();
    }

    public void desconnect() {
        handlerCommand.disconnect();
    }

    public void authorization(String login, String pass) {
        handlerCommand.authorization(login,pass);
    }

    public void getInfoFiles() {
        handlerCommand.getInfoFiles();
    }

    public void sendFile(String file) {
       handlerCommand.sendFile(file);
    }


   //************************************************************
    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }

    public boolean isAccessed() {
        return isAccessed;
    }

    public void setAccessed(boolean accessed) {
        isAccessed = accessed;
    }



}
