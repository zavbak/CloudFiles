package com.gladkih.geekbrains.cloudfiles.client.mvc.model;

import com.gladkih.geekbrains.cloudfiles.client.net.ClientNetty;
import com.gladkih.geekbrains.cloudfiles.common.command.AuthCommReq;
import com.gladkih.geekbrains.cloudfiles.common.command.GetInfoFilesReq;
import com.gladkih.geekbrains.cloudfiles.common.command.SendFileCommReq;
import com.gladkih.geekbrains.cloudfiles.common.helpers.FileHelper;
import com.gladkih.geekbrains.cloudfiles.common.helpers.HelperSendFiles;
import com.gladkih.geekbrains.cloudfiles.common.net.ListnerChanelHandler;
import com.gladkih.geekbrains.cloudfiles.common.net.Network;
import io.reactivex.Completable;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HandlerModelCommand implements ListnerChanelHandler {

    Model model;
    Config config;
    Network network;

    HelperSendFiles helperSendFiles;


    public HandlerModelCommand(Model model) {
        this.model = model;
        this.config = new Config();
        this.network = new ClientNetty(this, config.getHost(), config.getPort());
        this.helperSendFiles = HelperSendFiles.getInstance();
    }

    public void sendFile(String file) {

        Path path = Paths.get(file);

        if (!Files.exists(path)) {
            model.showMessage("Не нашел файл!");
        }

        try {
            long size = Files.size(path);
            if (size == 0) {
                model.showMessage("Пустой файл!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            helperSendFiles.sendFile(
                    path,
                    sendFileCommReq -> {
                        sendMessage(sendFileCommReq)
                                .subscribe(() -> {
                                    model.showMessage("Отправили часть: " + sendFileCommReq.getPart() + " файла" + sendFileCommReq.getFileName());
                                }, throwable -> {
                                    model.showMessage("Ошибка отправки части!");
                                    throwable.printStackTrace();
                                });
                    },
                    3);
        } catch (IOException e) {
            model.showMessage("Ошибка отправки файла!");
            e.printStackTrace();
        }
    }


    public void connect() {
        network.connect()
                .subscribe(() -> {
                    model.setConnect(true);
                    model.showMessage("Подключился!");
                }, throwable -> {
                    model.setConnect(false);
                    model.showMessage("Подключился!");
                });
    }

    public void authorization(String login, String pass) {
        if (!model.isConnect()) {
            model.showMessage("Нет подключения!");
            return;
        }

        sendMessage(new AuthCommReq(login, pass.hashCode()))
                .subscribe(() -> {
                    model.setAccessed(true);
                    model.showMessage("Авторизуюсь!");
                }, throwable -> {
                    model.setAccessed(false);
                    model.showMessage("Ошибка авторизации!");
                    throwable.printStackTrace();
                });
    }

    public void sendString(String str) {
        if (!model.isConnect()) {
            model.showMessage("Нет подключения!");
            return;
        }

        sendMessage(str)
                .subscribe(() -> {
                    model.showMessage("Отправлена строка: " + str);
                }, throwable -> {
                    model.showMessage("Ошибка отправления строки: " + str);
                    throwable.printStackTrace();
                });
    }

    public void start() {

    }

    public void disconnect() {
        network.close()
                .subscribe(() -> {

                }, throwable -> {
                    model.showMessage("Ошибка отключения канала!");
                    throwable.printStackTrace();
                });
    }

    public void getInfoFiles() {
        if (!model.isConnect()) {
            model.showMessage("Нет подключения!");
            return;
        }

        if (!model.isAccessed()) {
            model.showMessage("Нет авторизованы!");
            return;
        }

        sendMessage(new GetInfoFilesReq())
                .subscribe(() -> {
                    model.showMessage("Запросили файлы!");
                }, throwable -> {
                    model.showMessage("Ошибка запроса файлов!");
                    throwable.printStackTrace();
                });
    }

    private Completable sendMessage(Serializable serializable) {
        return network.send(serializable);
    }

    @Override
    public Serializable onReceive(Serializable o) {
        model.showMessage(o.toString());
        return null;
    }

    @Override
    public void onExeption(Throwable throwable) {
        model.desconnect();
        model.setConnect(false);
        model.setAccessed(false);
        model.showMessage("Ошибка канала связи!");
        throwable.printStackTrace();
    }


}
