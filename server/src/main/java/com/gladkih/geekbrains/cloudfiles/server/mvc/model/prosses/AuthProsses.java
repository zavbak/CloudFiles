package com.gladkih.geekbrains.cloudfiles.server.mvc.model.prosses;

import com.gladkih.geekbrains.cloudfiles.common.command.AuthCommReq;
import com.gladkih.geekbrains.cloudfiles.common.command.AuthCommRes;
import com.gladkih.geekbrains.cloudfiles.server.db.DBHelper;
import com.gladkih.geekbrains.cloudfiles.server.mvc.model.Model;
import com.gladkih.geekbrains.cloudfiles.server.mvc.model.User;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.exceptions.Exceptions;

import java.io.Serializable;
import java.sql.SQLException;


public class AuthProsses {

    public static Serializable response(Model model, AuthCommReq authCommand) throws SQLException {

        model.setUser(null);

        if (authCommand.getLogin() == null ||
                authCommand.getLogin().length() < 5) {

            return new AuthCommRes(null, true, "Ошибка длинны логина");

        } else if (authCommand.getPassHashCode() == 0) {

            return new AuthCommRes(null, true, "Пустой пароль!");

        }

        User user = null;

        user = model.getDbHelper().getUser(authCommand.getLogin());

        if (user == null) {
            model.getDbHelper().addUser(new User(authCommand.getLogin(), authCommand.getPassHashCode()));
            user = model.getDbHelper().getUser(authCommand.getLogin());


        } else if (user.getPassHashCode() != authCommand.getPassHashCode()) {

            return new AuthCommRes(null, true, "Неверный пароль!");

        }

        model.setUser(user);
        return new AuthCommRes(model.getUser().getLogin(), false, null);

    }
}
