package com.gladkih.geekbrains.cloudfiles.server.mvc.model;


import com.gladkih.geekbrains.cloudfiles.common.command.ErrorCommRes;
import com.gladkih.geekbrains.cloudfiles.common.net.ListnerChanelHandler;
import com.gladkih.geekbrains.cloudfiles.common.net.Network;
import com.gladkih.geekbrains.cloudfiles.server.db.DBHelper;
import com.gladkih.geekbrains.cloudfiles.server.mvc.model.prosses.RouterCommand;
import io.reactivex.Completable;


import java.io.Serializable;
import java.sql.SQLException;

public class Model implements ListnerChanelHandler {

    Network network;
    User user;
    RouterCommand routCommand;

    DBHelper dbHelper;

    public Model(Network network, DBHelper dbHelper) throws SQLException, ClassNotFoundException {
        this.network = network;
        this.routCommand = new RouterCommand(this);
        this.dbHelper = dbHelper;
    }

    @Override
    public Serializable onReceive(Serializable o) {
        return (Serializable) routCommand.action(o);
    }
    @Override
    public void onExeption(Throwable throwable) {
        throwable.printStackTrace();
    }

    public DBHelper getDbHelper() {
        return dbHelper;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Completable sendMessaga(Serializable serializable) {
        return network.send(serializable);
    }
}
