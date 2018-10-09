package com.gladkih.geekbrains.cloudfiles.common.command;

public class AuthCommRes extends AbsCommand {
    String login;


    public AuthCommRes(String login) {
        this.login    = login;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "AuthCommRes{" +
                "login='" + login + '\'' +
                '}';
    }
}
