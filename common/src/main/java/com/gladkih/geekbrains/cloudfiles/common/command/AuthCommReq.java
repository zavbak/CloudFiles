package com.gladkih.geekbrains.cloudfiles.common.command;

public class AuthCommReq extends AbsCommand {
    String login;
    int passHashCode;

    public AuthCommReq(String login, int passHashCode) {
        this.login    = login;
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
        return "AuthCommReq{" +
                "login='" + login + '\'' +
                ", passHashCode=" + passHashCode +
                '}';
    }
}
