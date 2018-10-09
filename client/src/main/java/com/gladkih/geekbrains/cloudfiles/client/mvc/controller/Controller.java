package com.gladkih.geekbrains.cloudfiles.client.mvc.controller;

import com.gladkih.geekbrains.cloudfiles.client.mvc.view.View;
import com.gladkih.geekbrains.cloudfiles.common.net.ListnerChanelHandler;
import com.gladkih.geekbrains.cloudfiles.client.mvc.model.Model;

import io.reactivex.Completable;

import java.io.Serializable;

public class Controller {

    Model model;
    View view;

    public Controller(View view) {
        model = new Model(this);
        this.view = view;
    }

    public void sendString(String str){
        model.sendString(str);
    }

    public void connect(){
        model.connect();
    }

    public void disconect(){
        model.desconnect();
    }

    public void authorization(String login, String pass){
       model.authorization(login,pass);
    }

    public void showMessage(String mess) {
        view.showMessage(mess);
    }

    public void getInfoFiles() {
        model.getInfoFiles();
    }

    public void sendFile(String file) {
        model.sendFile(file);
    }
}
