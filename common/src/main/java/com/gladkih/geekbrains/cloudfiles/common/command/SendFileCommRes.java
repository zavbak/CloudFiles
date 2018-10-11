package com.gladkih.geekbrains.cloudfiles.common.command;


public class SendFileCommRes extends AbsCommand {
    Boolean isError;
    String descriptionErr;
    int part;
    String fileName;
    int hasCode;
    int hasCodeFiles;

    public SendFileCommRes(Boolean isError, String descriptionErr, int part, String fileName, int hasCode,int hasCodeFiles) {
        this.isError = isError;
        this.descriptionErr = descriptionErr;
        this.part = part;
        this.fileName = fileName;
        this.hasCode = hasCode;
        this.hasCodeFiles = hasCodeFiles;
    }

    public int getHasCodeFiles() {
        return hasCodeFiles;
    }

    public Boolean getError() {
        return isError;
    }

    public String getDescriptionErr() {
        return descriptionErr;
    }

    public int getPart() {
        return part;
    }

    public String getFileName() {
        return fileName;
    }

    public int getHasCode() {
        return hasCode;
    }
}
