package com.gladkih.geekbrains.cloudfiles.common.command;

import java.util.Arrays;

public class SendFileCommReq extends AbsCommand {
    String fileName;
    byte[] data;
    long seek;

    public SendFileCommReq(String fileName, byte[] data, long seek) {
        this.fileName = fileName;
        this.data = data;
        this.seek = seek;
    }


    @Override
    public String toString() {
        return "SendFileCommReq{" +
                "fileName='" + fileName + '\'' +
                ", data=" + Arrays.toString(data) +
                ", seek=" + seek +
                '}';
    }
}
