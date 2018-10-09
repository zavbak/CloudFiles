package com.gladkih.geekbrains.cloudfiles.client.mvc.view;


import com.gladkih.geekbrains.cloudfiles.client.mvc.controller.Controller;
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


    @FXML
    TextField loginField;

    @FXML
    TextField passField;

    @FXML
    TextField fileField;

    Controller controller;


    public MainControllerView() {
        controller = new Controller(this);
    }


    public void connect() {
        controller.connect();
    }


    public void disconnect() {
        controller.disconect();
    }


    public void btnClickMeReaction() {
        controller.sendString(textField.getText());

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
        controller.authorization(loginField.getText(),passField.getText());
    }

    public void btnClickGetInfo(ActionEvent actionEvent) {
        controller.getInfoFiles();
    }

    public void btnSendFile(ActionEvent actionEvent) {
        controller.sendFile(fileField.getText());
    }
}
