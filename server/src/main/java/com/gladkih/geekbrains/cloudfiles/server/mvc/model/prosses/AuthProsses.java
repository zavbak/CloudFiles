package com.gladkih.geekbrains.cloudfiles.server.mvc.model.prosses;

import com.gladkih.geekbrains.cloudfiles.common.command.AuthCommReq;
import com.gladkih.geekbrains.cloudfiles.common.command.AuthCommRes;
import com.gladkih.geekbrains.cloudfiles.server.db.DBHelper;
import com.gladkih.geekbrains.cloudfiles.server.mvc.model.Model;
import com.gladkih.geekbrains.cloudfiles.server.mvc.model.User;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.exceptions.Exceptions;


public class AuthProsses {

    public static Single response(Model model, AuthCommReq authCommand) {
        return Single.just(authCommand)
                .map(authCommReq -> {

                    if (authCommand.getLogin() == null || authCommand.getLogin().length() < 5) {
                        throw Exceptions.propagate(new Throwable("Не верный Логин"));
                    } else if (authCommand.getPassHashCode() == 0) {
                        throw Exceptions.propagate(new Throwable("Пустой пароль!"));
                    }

                    User user = model.getDbHelper().getUser(authCommand.getLogin());

                    if (user == null) {
                        model.getDbHelper().addUser(new User(authCommand.getLogin(),
                                authCommand.getPassHashCode()));
                    }else if (user.getPassHashCode() != authCommand.getPassHashCode()){
                        throw Exceptions.propagate(new Throwable("Неверный пароль!"));
                    }

                    return user;

                }).map(user -> {
                    model.setUser((User) user);
                    return new AuthCommRes(model.getUser().getLogin());
                });

    }
}
