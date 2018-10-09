package com.gladkih.geekbrains.cloudfiles.client.mvc.view;


import com.gladkih.geekbrains.cloudfiles.client.mvc.controller.Controller;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;


public class MainControllerView implements View {
    @FXML
    TextField textField;

    @FXML
    TextArea textArea;

    Controller controller;


    public MainControllerView() {
        controller = new Controller(this);
    }


    public void connect() {
        controller.connect()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(() -> {
                    System.out.println("Connect");
                }, throwable -> {
                    System.out.println("Error");
                    throwable.printStackTrace();
                });
    }


    public void disconnect() {
        controller.disconect()
                .subscribe(() -> {
                    System.out.println("Close");
                }, throwable -> {
                    System.out.println("Error");
                    throwable.printStackTrace();
                });
    }


    public void btnClickMeReaction() {
        controller.sendMessage(textField.getText())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(() -> {
                    System.out.println("Send");
                }, throwable -> {
                    System.out.println("Error");
                    throwable.printStackTrace();
                });

        showMessage(textField.getText() + '\n');
        textField.clear();
        textField.requestFocus();
    }


    public EventHandler<WindowEvent> getCloseEventHandler() {
        return new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                disconnect();
            }
        };
    }

    public void btnClickConnect(ActionEvent actionEvent) {
        connect();
    }

    @Override
    public synchronized void showMessage(String str) {
        Platform.runLater(() -> {
            textArea.setText(textArea.getText() + "\n" + str);
        });

    }

    public void btnClickAuth(ActionEvent actionEvent) {
        controller.sendAuth()
                .subscribe(() -> {
                }, throwable -> {
                    throwable.printStackTrace();
                });
    }
}
