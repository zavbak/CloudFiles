package com.gladkih.geekbrains.cloudfiles.server.mvc.model.prosses;


import com.gladkih.geekbrains.cloudfiles.common.command.*;
import com.gladkih.geekbrains.cloudfiles.common.command.intity.InfoFile;
import com.gladkih.geekbrains.cloudfiles.server.mvc.model.Model;


import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public class RouterCommand {
    Model model;

    public RouterCommand(Model model) {
        this.model = model;
    }

    public Serializable action(Object o) {

        //авторизация
        if (o instanceof AuthCommReq) {
            try {
                return AuthProsses.response(model, (AuthCommReq) o);
            } catch (SQLException e) {
                e.printStackTrace();
                return new AuthCommRes(null, true, "Ошибка в авторизации");
            }
        }

        if (o instanceof GetInfoFilesReq) {
            try {
                return GetInfoFilesProsses.response(model);
            } catch (IOException e) {
                e.printStackTrace();
                return new GetInfoFilesRes(true,"Ошибка получение информации офайле!",null);
            }
        }

        if (o instanceof SendFileCommReq){
            try {
                return SendFileProsses.response(model, (SendFileCommReq) o);
            } catch (IOException e) {
                e.printStackTrace();
                return new GetInfoFilesRes(true,"Ошибка записи файла!",null);
            }
        }

        //Строка
        if (o instanceof String) {
            return (String) o;
        }

        return "Пришел неизвестный объект!";
    }
}
