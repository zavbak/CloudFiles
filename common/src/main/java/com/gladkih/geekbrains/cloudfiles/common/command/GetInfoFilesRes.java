package com.gladkih.geekbrains.cloudfiles.common.command;

import com.gladkih.geekbrains.cloudfiles.common.command.intity.InfoFile;

import java.util.Arrays;

public class GetInfoFilesRes extends AbsCommand {

    Boolean isError;
    String login;
    String descriptionErr;
    InfoFile[] infoFiles;


    public GetInfoFilesRes(Boolean isError, String descriptionErr, InfoFile[] infoFiles) {
        this.isError = isError;
        this.login = login;
        this.descriptionErr = descriptionErr;
        this.infoFiles = infoFiles;
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

    public InfoFile[] getInfoFiles() {
        return infoFiles;
    }

    @Override
    public String toString() {
        return "GetInfoFilesRes{" +
                "isError=" + isError +
                ", login='" + login + '\'' +
                ", descriptionErr='" + descriptionErr + '\'' +
                ", infoFiles=" + Arrays.toString(infoFiles) +
                '}';
    }
}
