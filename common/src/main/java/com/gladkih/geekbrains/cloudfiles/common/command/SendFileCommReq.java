package com.gladkih.geekbrains.cloudfiles.common.command;

import java.util.Arrays;

public class SendFileCommReq extends AbsCommand {
    String fileName;
    byte[] data;
    long seek;
    long part;


    public SendFileCommReq(String fileName, byte[] data, long seek, long part) {
        this.fileName = fileName;
        this.data = data;
        this.seek = seek;
        this.part = part;
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

    public long getPart() {
        return part;
    }

    @Override
    public String toString() {
        return "SendFileCommReq{" +
                "fileName='" + fileName + '\'' +
                ", data=" + Arrays.toString(data) +
                ", seek=" + seek +
                ", part=" + part +
                '}';
    }
}
