package com.gladkih.geekbrains.cloudfiles.server.mvc.model;



import java.io.Serializable;


public class User implements Serializable {
    String login;
    int passHashCode;

    public User(String login, int passHashCode) {
        this.login = login;
        this.passHashCode = passHashCode;
    }

    public String getLogin() {
        return login;
    }

    public int getPassHashCode() {
        return passHashCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", passHashCode=" + passHashCode +
                '}';
    }
}
