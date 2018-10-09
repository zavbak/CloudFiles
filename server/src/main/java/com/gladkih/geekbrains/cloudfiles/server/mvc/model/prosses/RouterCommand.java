package com.gladkih.geekbrains.cloudfiles.server.mvc.model.prosses;

import com.gladkih.geekbrains.cloudfiles.common.command.AbsCommand;
import com.gladkih.geekbrains.cloudfiles.common.command.AuthCommReq;
import com.gladkih.geekbrains.cloudfiles.common.command.ErrorCommRes;
import com.gladkih.geekbrains.cloudfiles.server.mvc.model.Model;
import com.gladkih.geekbrains.cloudfiles.server.mvc.model.prosses.AuthProsses;
import io.reactivex.Single;

import java.io.Serializable;

public class RouterCommand {
    Model model;

    public RouterCommand(Model model) {
        this.model = model;
    }

    public Single action(Object o) {
        if (o instanceof AuthCommReq) {
            return AuthProsses.response(model, (AuthCommReq) o);
        }

        return Single.error(() -> new Throwable("Unknown request!"));
    }
}
