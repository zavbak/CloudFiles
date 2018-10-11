package com.gladkih.geekbrains.cloudfiles.common.command;

import java.util.Arrays;

public class SendFileCommReq extends AbsCommand {
    String fileName;
    byte[] data;
    long seek;
    int part;
    int hasCode;

    public SendFileCommReq(String fileName, byte[] data, long seek, int part,int hasCode) {
        this.fileName = fileName;
        this.data = data;
        this.seek = seek;
        this.part = part;
        this.hasCode = hasCode;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getData() {
        return data;
    }

    public long getSeek() {
        return seek;
    }

    public int getPart() {
        return part;
    }

    public int getHasCode() {
        return hasCode;
    }
}
