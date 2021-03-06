package com.gladkih.geekbrains.cloudfiles.common.command;

public class AuthCommRes extends AbsCommand {
    Boolean isError;
    String login;
    String descriptionErr;


    public AuthCommRes(String login, Boolean isError,String descriptionErr ) {
        this.login    = login;
        this.isError = isError;
        this.descriptionErr = descriptionErr;
    }

    public Boolean getError() {
        return isError;
    }

    public String getLogin() {
        return login;
    }

    public String getDescriptionErr() {
        return descriptionErr;
    }

    @Override
    public String toString() {
        return "AuthCommRes{" +
                "isError=" + isError +
                ", login='" + login + '\'' +
                ", descriptionErr='" + descriptionErr + '\'' +
                '}';
    }
}
